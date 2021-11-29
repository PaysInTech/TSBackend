package app.techsalaries.api.user

import app.techsalaries.api.response.Success
import app.techsalaries.api.user.model.AuthResponse
import app.techsalaries.api.user.model.UserLoginRequest
import app.techsalaries.api.user.model.UserRegistrationRequest
import app.techsalaries.api.user.model.UserResponse
import app.techsalaries.core.user.UserService
import app.techsalaries.core.user.model.AddNewUser
import app.techsalaries.core.user.model.User
import app.techsalaries.exception.AuthenticationException
import app.techsalaries.exception.CommonErrors
import app.techsalaries.exception.badRequest
import app.techsalaries.http.exception.InvalidCredentialsException
import app.techsalaries.http.exception.UserAlreadyExistException
import app.techsalaries.http.firebase.authentication.AuthenticationManager
import app.techsalaries.http.firebase.authentication.model.AuthTokenAndUser
import app.techsalaries.utils.handleResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserController @Inject constructor(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {
    suspend fun register(request: UserRegistrationRequest) = handleResponse<AuthResponse> {
        try {
            // Check if user with username already exists or not
            val userAlreadyExists = runCatching { userService.findByUsername(request.username) }.getOrNull() != null
            if (userAlreadyExists) {
                badRequest(CommonErrors.USERNAME_ALREADY_EXISTS)
            }
            val auth = authenticationManager.createUser(request.username, request.email, request.password)
            try {
                auth.authUser.let { userService.addUser(AddNewUser(it.uid, it.displayName, it.email)) }
                Success(auth.toAuthResponse())
            } catch (e: Exception) {
                // If service fails to add user it means our system has not stored user. In such case, we should delete
                // the created user from firebase as well. Otherwise there'll be inconsistency.
                authenticationManager.deleteUser(auth.authUser.uid)
                throw e
            }
        } catch (e: UserAlreadyExistException) {
            badRequest(CommonErrors.EMAIL_ALREADY_EXISTS)
        }
    }

    suspend fun login(request: UserLoginRequest) = handleResponse<AuthResponse> {
        try {
            val user = userService.findByUsername(request.username)
            val auth = authenticationManager.signInByEmailAndPassword(user.email, request.password)
            Success(auth.toAuthResponse())
        } catch (e: InvalidCredentialsException) {
            throw AuthenticationException(e.message.toString())
        }
    }

    suspend fun getLoggedInUser(user: User) = handleResponse<UserResponse> {
        Success(UserResponse(user.username, user.email, user.totalCoins, user.roles.map { it.roleName }))
    }

    private fun AuthTokenAndUser.toAuthResponse() = AuthResponse(token, refreshToken, expiresIn)

    suspend fun refreshTokenForToken(refreshToken: String) = handleResponse {
        Success(authenticationManager.refreshTokens(refreshToken).toAuthResponse())
    }
}
