package app.paysintech.api.health.model

import app.paysintech.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(val status: String) : BaseResponse(true) {
    companion object {
        fun ok() = HealthResponse("OK")
    }
}
