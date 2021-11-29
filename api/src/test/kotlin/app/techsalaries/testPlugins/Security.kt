package app.techsalaries.testPlugins

import app.techsalaries.api.authentication.Authenticator
import app.techsalaries.api.authentication.UserPrincipal
import app.techsalaries.core.user.model.User
import app.techsalaries.core.user.model.UserRole
import app.techsalaries.exception.authenticationError
import app.techsalaries.plugins.AUTHENTICATION_NO_STRICT
import app.techsalaries.plugins.AUTHENTICATION_STRICT
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.AuthenticationPipeline
import io.ktor.auth.AuthenticationProvider
import io.ktor.auth.Principal
import io.ktor.request.authorization

fun Application.configureTestSecurity(authenticator: Authenticator) {
    install(Authentication) {
        testAuthentication(AUTHENTICATION_STRICT, throwOnAbsentToken = true) { this.authenticator = authenticator }
        testAuthentication(AUTHENTICATION_NO_STRICT, throwOnAbsentToken = false) { this.authenticator = authenticator }
    }
}

val authenticatedUser = UserPrincipal(
    User(
        id = "user1234",
        username = "user1234",
        email = "example@email.com",
        totalCoins = 1000,
        roles = listOf(UserRole.MEMBER)
    )
)

class TestAuthenticationProvider private constructor(
    private val config: Configuration
) : AuthenticationProvider(config) {

    suspend fun provideUserPrincipal(token: String): Principal {
        if (token == "valid-token") {
        }
        return config.authenticator.authenticate(token).let { UserPrincipal(it) }
    }

    class Configuration internal constructor(name: String?) : AuthenticationProvider.Configuration(name) {
        lateinit var authenticator: Authenticator

        fun build() = TestAuthenticationProvider(this)
    }
}

fun Authentication.Configuration.testAuthentication(
    name: String? = null,
    throwOnAbsentToken: Boolean,
    configure: TestAuthenticationProvider.Configuration.() -> Unit
) {
    val provider = TestAuthenticationProvider.Configuration(name).apply(configure).build()
    provider.pipeline.intercept(AuthenticationPipeline.RequestAuthentication) { context ->
        val token = call.request.authorization()?.split(" ")?.lastOrNull()
        if (token == null && throwOnAbsentToken) {
            authenticationError()
        }
        token?.let { context.principal(provider.provideUserPrincipal(token)) }
    }
    register(provider)
}
