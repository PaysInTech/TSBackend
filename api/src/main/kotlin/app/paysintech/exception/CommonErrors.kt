package app.paysintech.exception

object CommonErrors {
    const val INVALID_BENEFIT_ERROR = "Benefits should not be greater than annual CTC ot inhand per month salary"
    const val DATE_PARSE_ERROR = "Invalid date specific. Date should be in 'YYYY-DD-MM' format"
    const val INVALID_IN_HAND_SALARY_ERROR = "In-hand salary should not be greater than (CTC/12)"
    const val INVALID_WORK_TYPE_ERROR = "Work type should be one of (FULL_REMOTE, HYBRID, OFFICE)"
    const val INVALID_JOB_LEVEL = "Level should be from L1 to L10"
    const val INVALID_EMAIL = "Email is invalid"
    const val INVALID_USERNAME = "Username should at least have 4 characters and should not contain special characters"
    const val INVALID_PASSWORD = "Password should at least have 8 characters"
    const val EMAIL_ALREADY_EXISTS = "User already exists with this email"
    const val USERNAME_ALREADY_EXISTS = "User already exists with this username"
    const val INVALID_AUTHENTICATION_TOKEN = "Invalid authentication token!"
    const val INVALID_CURRENCY_CODE = "Provided currency code is Invalid"
}
