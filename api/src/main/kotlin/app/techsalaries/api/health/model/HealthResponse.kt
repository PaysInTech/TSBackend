package app.techsalaries.api.health.model

import app.techsalaries.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(val status: String) : BaseResponse(true) {
    companion object {
        fun ok() = HealthResponse("OK")
    }
}
