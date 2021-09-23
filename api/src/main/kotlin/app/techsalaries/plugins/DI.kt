package app.techsalaries.plugins

import app.techsalaries.di.component.AppComponent
import app.techsalaries.di.component.ControllerComponent
import app.techsalaries.di.component.DaggerAppComponent
import io.ktor.application.Application
import io.ktor.routing.Route
import io.ktor.routing.application
import io.ktor.util.AttributeKey

fun Application.configureDI() {
    val appComponent = DaggerAppComponent.builder().withApplication(this).build()
    setAppComponent(appComponent)
    setControllerComponent(appComponent.controllerComponent())
}

val controllerComponentKey = AttributeKey<ControllerComponent>("TECH_SALARIES_CONTROLLER_COMPONENT")
val appComponentKey = AttributeKey<AppComponent>("TECH_SALARIES_APP_COMPONENT")

private fun Application.setControllerComponent(controllerComponent: ControllerComponent) {
    attributes.put(controllerComponentKey, controllerComponent)
}

private fun Application.setAppComponent(appComponent: AppComponent) {
    attributes.put(appComponentKey, appComponent)
}

/**
 * Retrieves [ControllerComponent] from Application scope
 */
val Application.controllers: ControllerComponent get() = attributes[controllerComponentKey]

/**
 * Retrieves [AppComponent] from Application scope
 */
val Application.appComponent: AppComponent get() = attributes[appComponentKey]

/**
 * Retrieves [ControllerComponent] from Route scope
 */
val Route.controllers: ControllerComponent get() = application.attributes[controllerComponentKey]
