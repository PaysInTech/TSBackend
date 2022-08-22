package app.techsalaries.core.user

import io.ktor.server.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String,
    val displayName: String,
    val email: String,
    val passwordHash: String
) : Principal
