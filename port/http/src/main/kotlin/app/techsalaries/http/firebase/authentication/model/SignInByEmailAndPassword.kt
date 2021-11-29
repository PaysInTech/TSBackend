package app.techsalaries.http.firebase.authentication.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInByEmailAndPasswordRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)

@Serializable
data class SignInByEmailAndPasswordResponse(
    val idToken: String,
    val email: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String,
    val registered: Boolean
)