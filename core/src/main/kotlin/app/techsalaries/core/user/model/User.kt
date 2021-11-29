package app.techsalaries.core.user.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val totalCoins: Long,
    val roles: List<UserRole> = emptyList()
)
