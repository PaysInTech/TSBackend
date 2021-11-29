package app.techsalaries.api.user.model

import app.techsalaries.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val refreshToken: String,
    val expiresIn: String
) : BaseResponse(true)
