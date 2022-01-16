package app.techsalaries.api.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: Int? = null,
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
) {
    fun hashedPassword(): String = password

    fun isValidCred() = username.length >= 3 && password.length >= 6
}
