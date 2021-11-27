package app.techsalaries.exception

object CommonErrors {
    const val INVALID_ERRORS = "Benefits should not be greater than annual CTC ot inhand per month salary"
    const val DATE_PARSE_ERROR = "Invalid date specific. Date should be in 'YYYY-DD-MM' format"
    const val INVALID_IN_HAND_SALARY_ERROR = "In-hand salary should not be greater than (CTC/12)"
    const val INVALID_WORK_TYPE_ERROR = "Work type should be one of (FULL_REMOTE, HYBRID, OFFICE)"
    const val INVALID_JOB_LEVEL = "Level should be from L1 to L10"
}
