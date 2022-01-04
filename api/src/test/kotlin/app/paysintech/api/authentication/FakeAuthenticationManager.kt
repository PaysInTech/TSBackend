package app.paysintech.api.authentication

import app.paysintech.api.user.fixtures.authenticatedUser
import app.paysintech.api.user.fixtures.userRegistrationRequest
import app.paysintech.http.exception.InvalidCredentialsException
import app.paysintech.http.exception.UserAlreadyExistException
import app.paysintech.http.firebase.authentication.AuthenticationManager
import app.paysintech.http.firebase.authentication.model.AuthTokenAndUser
import app.paysintech.http.firebase.authentication.model.AuthenticatedUser
import javax.inject.Inject

class FakeAuthenticationManager @Inject constructor() : AuthenticationManager {
    private val validAuthRepsonse = AuthTokenAndUser("valid-token", "refresh-token", "3600", authenticatedUser())

    override suspend fun createUser(username: String, email: String, password: String): AuthTokenAndUser {
        val expectedRequest = userRegistrationRequest()

        return if (username == expectedRequest.username && email == expectedRequest.email && password == expectedRequest.password) {
            validAuthRepsonse
        } else {
            throw UserAlreadyExistException()
        }
    }

    override suspend fun deleteUser(uid: String) {}

    override suspend fun verifyToken(token: String): AuthenticatedUser {
        if (token == VALID_TOKEN) return validAuthRepsonse.authUser else error("Invalid Token")
    }

    override suspend fun refreshTokens(refreshToken: String): AuthTokenAndUser {
        return if (refreshToken == VALID_TOKEN) validAuthRepsonse else error("Invalid refresh token")
    }

    override suspend fun signInByEmailAndPassword(email: String, password: String): AuthTokenAndUser {
        return if (email == AuthenticatedUser.email && password == "valid-password") {
            validAuthRepsonse
        } else throw InvalidCredentialsException()
    }
}
