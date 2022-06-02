package app.paysintech.api.salary.model

import app.paysintech.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class AddSalaryDetailResponse(
    val id: Long,
    val coinsEarned: Int,
) : BaseResponse(true)
