package app.techsalaries.core.user.model

enum class UserRole(val roleName: String) {
    ADMIN("Admin"), MEMBER("Member");

    companion object {
        fun byRoleName(roleName: String) = values().find { it.roleName == roleName }
    }
}
