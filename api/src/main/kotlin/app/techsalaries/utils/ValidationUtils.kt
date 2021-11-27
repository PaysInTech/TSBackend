package app.techsalaries.utils

import app.techsalaries.exception.BadRequestException

/**
 * If given [value] is false then throws [BadRequestException] with specified [lazyMessage]
 */
inline fun validate(value: Boolean, lazyMessage: () -> Any? = { "" }) {
    if (!value) {
        throw BadRequestException(lazyMessage().toString())
    }
}
