package app.techsalaries.http.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

val HttpClient = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        })
    }
}