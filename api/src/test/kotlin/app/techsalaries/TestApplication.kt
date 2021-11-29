package app.techsalaries

import app.techsalaries.di.component.DaggerTestAppComponent
import app.techsalaries.di.component.TestAppComponent
import app.techsalaries.plugins.configureDI
import app.techsalaries.plugins.configureHTTP
import app.techsalaries.plugins.configureRouting
import app.techsalaries.plugins.configureSerialization
import app.techsalaries.plugins.configureStatusPages
import app.techsalaries.testPlugins.configureTestSecurity
import io.ktor.application.Application
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

fun <R> withTechSalariesTestApp(test: (TestApplicationEngine).() -> R) = withTestApplication({ testModule() }, test)

lateinit var testAppComponent: TestAppComponent

fun Application.testModule() {
    testAppComponent = DaggerTestAppComponent.builder().withApplication(this).build()
    configureDI(testAppComponent)
    configureTestSecurity(testAppComponent.authenticator())
    configureRouting()
    configureStatusPages()
    configureSerialization()
    configureHTTP()
}
