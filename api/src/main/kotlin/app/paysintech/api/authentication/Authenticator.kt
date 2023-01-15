package app.paysintech.api.authentication

import app.paysintech.core.user.UserService
import app.paysintech.core.user.model.User
import app.paysintech.exception.CommonErrors
import app.paysintech.exception.authenticationError
import app.paysintech.http.firebase.authentication.AuthenticationManager
import javax.inject.Inject

interface Authenticator {
    /**
     * Authenticates [token] and returns [User]
     */
    suspend fun authenticate(token: String): User
}

/**
 * [Authenticator] implementation using Firebase authentication authenticator
 */
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
