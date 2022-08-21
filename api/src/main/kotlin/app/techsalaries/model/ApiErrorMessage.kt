import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorMessage(
    val errorMessage: String
)
