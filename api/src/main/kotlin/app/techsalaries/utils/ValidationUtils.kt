package app.techsalaries.utils

import app.techsalaries.exception.BadRequestException
import java.util.regex.Pattern

/**
 * If given [value] is false then throws [BadRequestException] with specified [lazyMessage]
 */
inline fun validate(value: Boolean, lazyMessage: () -> Any? = { "" }) {
    if (!value) {
        throw BadRequestException(lazyMessage().toString())
    }
}

fun isValidEmail(email: String): Boolean = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
    .toPattern(Pattern.CASE_INSENSITIVE)
    .matcher(email)
    .matches()
