package app.techsalaries.api.authentication

import app.techsalaries.core.user.UserService
import app.techsalaries.core.user.model.User
import app.techsalaries.exception.CommonErrors
import app.techsalaries.exception.authenticationError
import app.techsalaries.http.firebase.authentication.AuthenticationManager
import javax.inject.Inject

interface Authenticator {
    suspend fun authenticate(token: String): User
}

class FirebaseAuthenticator @Inject constructor(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) : Authenticator {

    override suspend fun authenticate(token: String): User {
        try {
            val uid = authenticationManager.verifyToken(token).uid
            return userService.findById(uid)
        } catch (e: Exception) {
            authenticationError(CommonErrors.INVALID_AUTHENTICATION_TOKEN)
        }
    }
}
