package app.techsalaries.core.salary.model

enum class WorkType(val value: String) {
    FULL_REMOTE("FULL_REMOTE"),
    HYBRID("HYBRID"),
    OFFICE("OFFICE");

    companion object {
        fun by(value: String) = WorkType.values().find { it.value == value }
    }
}
