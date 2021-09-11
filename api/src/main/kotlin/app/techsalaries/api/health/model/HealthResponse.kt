package app.techsalaries.api.health.model

import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(val status: String) {
    companion object {
        fun ok() = HealthResponse("OK")
    }
}
