package app.paysintech

import app.paysintech.di.component.DaggerTestAppComponent
import app.paysintech.di.component.TestAppComponent
import app.paysintech.plugins.configureDI
import app.paysintech.plugins.configureHTTP
import app.paysintech.plugins.configureRouting
import app.paysintech.plugins.configureSecurity
import app.paysintech.plugins.configureSerialization
import app.paysintech.plugins.configureStatusPages
import app.paysintech.testData.setupTestData
import io.ktor.application.Application
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

fun <R> withPaysInTechTestApp(test: (TestApplicationEngine).() -> R) = withTestApplication({ testModule() }, test)

lateinit var testAppComponent: TestAppComponent

fun Application.testModule() {
    testAppComponent = DaggerTestAppComponent.builder().withApplication(this).build()
    configureDI(testAppComponent)
    configureSecurity(testAppComponent.authenticator())
    configureRouting()
    configureStatusPages()
    configureSerialization()
    configureHTTP()
    setupTestData(testAppComponent.dataSource())
}
