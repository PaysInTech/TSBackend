package app.techsalaries

import app.techsalaries.di.component.DaggerTestAppComponent
import app.techsalaries.plugins.configureDI
import app.techsalaries.plugins.configureHTTP
import app.techsalaries.plugins.configureRouting
import app.techsalaries.plugins.configureSecurity
import app.techsalaries.plugins.configureSerialization
import app.techsalaries.plugins.configureStatusPages
import io.ktor.application.Application
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

fun <R> withTechSalariesTestApp(test: (TestApplicationEngine).() -> R) = withTestApplication({ testModule() }, test)

fun Application.testModule() {
    configureDI(DaggerTestAppComponent.builder().withApplication(this).build())
    configureRouting()
    configureStatusPages()
    configureSerialization()
    configureSecurity()
    configureHTTP()
}
