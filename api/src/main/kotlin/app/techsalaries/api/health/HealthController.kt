package app.techsalaries.api.health

import app.techsalaries.api.health.model.HealthResponse
import app.techsalaries.api.response.BaseResponse
import app.techsalaries.api.response.HttpResponse
import app.techsalaries.api.response.Success
import app.techsalaries.api.response.Unsuccessful
import io.ktor.http.HttpStatusCode
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

/**
 * Controller for checking Health of the application.
 * It considers self (application) and all its dependent ports (like databases, etc.) for health checking.
 */
@Singleton
class HealthController @Inject constructor(private val dataSource: DataSource) {
    fun checkHealth(): HttpResponse<out BaseResponse> {
        // Because at this moment we are in an API call, so we don't need to validate API call.
        // We should directly check the health of dependencies
        val isDatabaseWorking = isDatabaseWorking()

        return if (!isDatabaseWorking) {
            Unsuccessful("Database is not operational", HttpStatusCode.ServiceUnavailable)
        } else {
            Success(HealthResponse.ok())
        }
    }

    private fun isDatabaseWorking(): Boolean = runCatching {
        dataSource.connection.use { connection ->
            connection.createStatement().executeQuery("SELECT true").next()
        }
    }.getOrDefault(false)
}