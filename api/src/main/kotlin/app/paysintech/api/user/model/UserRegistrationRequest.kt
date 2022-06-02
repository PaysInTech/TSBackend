package app.paysintech.api.user.model

import app.paysintech.exception.CommonErrors
import app.paysintech.utils.isValidEmail
import app.paysintech.utils.validate
import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationRequest(
    val username: String,
    val email: String,
    val password: String
) {
    init {
        validate(isValidEmail(email)) { CommonErrors.INVALID_EMAIL }
        validate(username.trim().length >= 4 && username.all { it.isLetterOrDigit() }) { CommonErrors.INVALID_USERNAME }
        validate(password.trim().length >= 8) { CommonErrors.INVALID_PASSWORD }
    }
}
