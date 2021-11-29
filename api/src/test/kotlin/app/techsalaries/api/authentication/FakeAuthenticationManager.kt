package app.techsalaries.api.authentication

import app.techsalaries.http.firebase.authentication.AuthenticationManager
import app.techsalaries.http.firebase.authentication.model.AuthTokenAndUser
import app.techsalaries.http.firebase.authentication.model.AuthenticatedUser
import javax.inject.Inject

class FakeAuthenticationManager @Inject constructor() : AuthenticationManager {
    private var createUserResponse: AuthTokenAndUser? = null
    private var createUserError: Throwable? = null
    private var verifyTokenResponse: AuthenticatedUser? = null
    private var refreshTokenResponse: AuthTokenAndUser? = null
    private var signInResponse: AuthTokenAndUser? = null
    private var signInError: Throwable? = null

    override suspend fun createUser(username: String, email: String, password: String): AuthTokenAndUser {
        return createUserResponse ?: throw createUserError!!
    }

    override suspend fun deleteUser(uid: String) {}

    override suspend fun verifyToken(token: String): AuthenticatedUser {
        if (token == "valid-token") return verifyTokenResponse!! else error("Invalid Token")
    }

    override suspend fun refreshTokens(refreshToken: String): AuthTokenAndUser {
        return refreshTokenResponse!!
    }

    override suspend fun signInByEmailAndPassword(email: String, password: String): AuthTokenAndUser {
        return signInResponse ?: throw signInError!!
    }

    fun provideFakeResponseForCreateUser(response: AuthTokenAndUser? = null, error: Throwable? = null) {
        createUserResponse = response
        createUserError = error
    }

    fun provideFakeResponseVerifyToken(response: AuthenticatedUser? = null) {
        verifyTokenResponse = response
    }

    fun provideFakeResponseForSignIn(response: AuthTokenAndUser? = null, error: Throwable? = null) {
        signInResponse = response
        signInError = error
    }
}
