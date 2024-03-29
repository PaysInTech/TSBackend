package app.paysintech.api.health

import app.paysintech.api.health.model.HealthResponse
import app.paysintech.api.response.BaseResponse
import app.paysintech.api.response.HttpResponse
import app.paysintech.api.response.Success
import app.paysintech.api.response.Unsuccessful
import app.paysintech.db.utils.sql.executeQuery
import app.paysintech.firebase.FirebaseInitializer
import io.ktor.http.HttpStatusCode
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import javax.sql.DataSource

/**
 * Controller for checking Health of the application.
 * It considers self (application) and all its dependent ports (like databases, etc.) for health checking.
 */
@Singleton
class HealthController @Inject constructor(
    private val dataSource: DataSource,
    private val firebaseInitializer: Provider<FirebaseInitializer>
) {
    fun checkHealth(): HttpResponse<out BaseResponse> {
        return when {
            !isDatabaseWorking() -> Unsuccessful("Database is not operational", HttpStatusCode.ServiceUnavailable)
            !isFirebaseWorking() -> Unsuccessful(
                "Firebase service is not operational",
                HttpStatusCode.ServiceUnavailable
            )
            else -> Success(HealthResponse.ok())
        }
    }

    private fun isDatabaseWorking(): Boolean = runCatching {
        dataSource.connection.use { connection ->
            connection.executeQuery("SELECT true").next()
        }
    }.getOrDefault(false)

    private fun isFirebaseWorking(): Boolean =
        runCatching { firebaseInitializer.get().firebaseApp != null }.getOrDefault(false)
}
