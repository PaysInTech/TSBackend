package app.techsalaries.api.user.model

import app.techsalaries.exception.CommonErrors
import app.techsalaries.utils.isValidEmail
import app.techsalaries.utils.validate
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
