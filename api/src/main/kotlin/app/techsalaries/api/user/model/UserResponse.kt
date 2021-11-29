package app.techsalaries.api.user.model

import app.techsalaries.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val username: String,
    val email: String,
    val totalCoins: Long,
    val roles: List<String>
) : BaseResponse(true)
