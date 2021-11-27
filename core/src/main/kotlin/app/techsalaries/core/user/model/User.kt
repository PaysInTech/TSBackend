package app.techsalaries.core.user.model

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<UserRole>
)
