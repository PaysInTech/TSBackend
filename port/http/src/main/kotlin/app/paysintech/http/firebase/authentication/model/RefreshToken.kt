package app.paysintech.http.firebase.authentication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    @SerialName("refresh_token")
    val refreshToken: String,

    @SerialName("grant_type")
    val grantType: String = "refresh_token"
)

@Serializable
data class RefreshTokenResponse(
    @SerialName("expires_in")
    val expiresIn: String,

    @SerialName("token_type")
    val tokenType: String,

    @SerialName("refresh_token")
    val refreshToken: String,

    @SerialName("id_token")
    val token: String,

    @SerialName("user_id")
    val userId: String
)
