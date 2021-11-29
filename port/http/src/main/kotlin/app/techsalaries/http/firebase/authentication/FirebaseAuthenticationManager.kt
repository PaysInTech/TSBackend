package app.techsalaries.http.firebase.authentication

import app.techsalaries.http.exception.InvalidCredentialsException
import app.techsalaries.http.exception.UserAlreadyExistException
import app.techsalaries.http.firebase.authentication.client.FirebaseAuthApiClient
import app.techsalaries.http.firebase.authentication.model.AuthTokenAndUser
import app.techsalaries.http.firebase.authentication.model.AuthenticatedUser
import app.techsalaries.http.firebase.authentication.model.RefreshTokenRequest
import app.techsalaries.http.firebase.authentication.model.SignInByEmailAndPasswordRequest
import com.google.firebase.auth.AuthErrorCode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseToken
import com.google.firebase.auth.UserRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface AuthenticationManager {
    suspend fun createUser(username: String, email: String, password: String): AuthTokenAndUser
    suspend fun deleteUser(uid: String)
    suspend fun verifyToken(token: String): AuthenticatedUser
    suspend fun refreshTokens(refreshToken: String): AuthTokenAndUser
    suspend fun signInByEmailAndPassword(email: String, password: String): AuthTokenAndUser
}

@Singleton
class FirebaseAuthenticationManager @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseAuthApiClient: FirebaseAuthApiClient
) : AuthenticationManager {

    override suspend fun createUser(username: String, email: String, password: String) = try {
        firebaseAuth.createUser(UserRecord.CreateRequest().apply {
            setDisplayName(username)
            setEmail(email)
            setPassword(password)
            setEmailVerified(false)
        })
        signInByEmailAndPassword(email, password)
    } catch (e: FirebaseAuthException) {
        when (e.authErrorCode) {
            AuthErrorCode.EMAIL_ALREADY_EXISTS -> throw UserAlreadyExistException()
            else -> throw e
        }
    }

    override suspend fun deleteUser(uid: String) {
        firebaseAuth.deleteUser(uid)
    }

    override suspend fun verifyToken(token: String) = try {
        firebaseAuth.verifyIdToken(token, true).toAuthenticatedUser()
    } catch (e: FirebaseAuthException) {
        error("Invalid token")
    }

    override suspend fun refreshTokens(refreshToken: String): AuthTokenAndUser {
        return firebaseAuthApiClient.refreshToken(RefreshTokenRequest(refreshToken)).let {
            val user = firebaseAuth.getUser(it.userId).toAuthenticatedUser()
            AuthTokenAndUser(it.token, it.refreshToken, it.expiresIn, user)
        }
    }

    override suspend fun signInByEmailAndPassword(email: String, password: String): AuthTokenAndUser = try {
        firebaseAuthApiClient.signInByEmailAndPassword(SignInByEmailAndPasswordRequest(email, password))
            .let {
                AuthTokenAndUser(
                    it.idToken,
                    it.refreshToken,
                    it.expiresIn,
                    firebaseAuth.getUser(it.localId).toAuthenticatedUser()
                )
            }
    } catch (e: Exception) {
        e.printStackTrace()
        throw InvalidCredentialsException()
    }

    private fun UserRecord.toAuthenticatedUser() =
        AuthenticatedUser(uid, displayName, email, isEmailVerified, customClaims)

    private fun FirebaseToken.toAuthenticatedUser() = AuthenticatedUser(uid, name, email, isEmailVerified, claims)
}