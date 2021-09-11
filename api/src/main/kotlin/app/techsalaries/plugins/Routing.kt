@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.techsalaries.plugins

import app.techsalaries.api.MainRoute
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.routing

fun Application.configureRouting() {
  install(Locations) {}
  routing { MainRoute() }
}

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}")
data class Type(val name: String) {
  @Location("/edit") data class Edit(val type: Type)

  @Location("/list/{page}") data class List(val type: Type, val page: Int)
}
