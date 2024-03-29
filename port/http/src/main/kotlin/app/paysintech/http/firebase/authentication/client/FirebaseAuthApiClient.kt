package app.paysintech.http.firebase.authentication.client

import app.paysintech.http.firebase.authentication.model.RefreshTokenRequest
import app.paysintech.http.firebase.authentication.model.RefreshTokenResponse
import app.paysintech.http.firebase.authentication.model.SignInByEmailAndPasswordRequest
import app.paysintech.http.firebase.authentication.model.SignInByEmailAndPasswordResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthApiClient @Inject constructor(
    private val firebaseApiKey: FirebaseApiKey,
    private val httpClient: HttpClient
) {
    suspend fun signInByEmailAndPassword(
        request: SignInByEmailAndPasswordRequest
    ): SignInByEmailAndPasswordResponse {
        return httpClient.post("$BASE_URL/:signInWithPassword?key=${firebaseApiKey.value}") {
            contentType(ContentType.Application.Json)
            body = request
        }
    }

    suspend fun refreshToken(request: RefreshTokenRequest): RefreshTokenResponse {
        return httpClient.submitForm(
            url = "https://securetoken.googleapis.com/v1/token?key=${firebaseApiKey.value}",
            formParameters = Parameters.build {
                append("grant_type", request.grantType)
                append("refresh_token", request.refreshToken)
            }
        )
    }

    companion object {
        const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts"
    }
}
