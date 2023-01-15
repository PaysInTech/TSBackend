package app.paysintech.api.user.model

import app.paysintech.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val username: String,
    val email: String,
    val totalCoins: Long,
    val roles: List<String>
) : BaseResponse(true)
