package app.paysintech.http.firebase.authentication.model

data class AuthenticatedUser(
    val uid: String,
    val displayName: String,
    val email: String,
    val emailVerified: Boolean,
    val claims: Map<String, Any> = emptyMap()
)

data class AuthTokenAndUser(
    val token: String,
    val refreshToken: String,
    val expiresIn: String,
    val authUser: AuthenticatedUser
)
