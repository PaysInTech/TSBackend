package app.techsalaries.api.salary.model

import app.techsalaries.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class AddSalaryDetailResponse(
    val id: Long,
    val coinsEarned: Int,
) : BaseResponse(true)
