package app.paysintech.api.user.model

import app.paysintech.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val refreshToken: String,
    val expiresIn: String
) : BaseResponse(true)
