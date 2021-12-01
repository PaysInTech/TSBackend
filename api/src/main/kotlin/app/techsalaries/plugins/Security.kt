package app.techsalaries.plugins

import app.techsalaries.api.authentication.Authenticator
import app.techsalaries.api.authentication.UserPrincipal
import app.techsalaries.exception.authenticationError
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.AuthenticationPipeline
import io.ktor.auth.AuthenticationProvider
import io.ktor.auth.Principal
import io.ktor.request.authorization

class FirebaseAuthenticationProvider private constructor(
    private val config: Configuration
) : AuthenticationProvider(config) {

    suspend fun provideUserPrincipal(token: String): Principal {
        return config.authenticator.authenticate(token).let { UserPrincipal(it) }
    }

    class Configuration internal constructor(name: String?) : AuthenticationProvider.Configuration(name) {
        lateinit var authenticator: Authenticator

        fun build() = FirebaseAuthenticationProvider(this)
    }
}

fun Authentication.Configuration.firebase(
    name: String? = null,
    throwOnAbsentToken: Boolean,
    configure: FirebaseAuthenticationProvider.Configuration.() -> Unit
) {
    val provider = FirebaseAuthenticationProvider.Configuration(name).apply(configure).build()
    provider.pipeline.intercept(AuthenticationPipeline.RequestAuthentication) { context ->
        val token = call.request.authorization()?.split(" ")?.lastOrNull()
        if (token == null && throwOnAbsentToken) {
            authenticationError()
        }
        token?.let { context.principal(provider.provideUserPrincipal(token)) }
    }
    register(provider)
}

fun Application.configureSecurity(authenticator: Authenticator = appComponent.authenticator()) {
    install(Authentication) {
        // This authenticator will throw exception if token is absent. Useful for strict APIs where authentication
        // is compulsory
        firebase(AUTHENTICATION_STRICT, throwOnAbsentToken = true) { this.authenticator = authenticator }

        // This authenticator will NOT throw exception if token is absent. Useful for non-strict APIs where
        // authentication is not mandatory or compulsory
        firebase(AUTHENTICATION_NO_STRICT, throwOnAbsentToken = false) { this.authenticator = authenticator }
    }
}

const val AUTHENTICATION_STRICT = "AUTH_STRICT"
const val AUTHENTICATION_NO_STRICT = "AUTH_NO_STRICT"
