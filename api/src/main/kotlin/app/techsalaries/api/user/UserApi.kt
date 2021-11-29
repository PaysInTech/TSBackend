package app.techsalaries.api.user

import app.techsalaries.api.TechSalariesApiRoute
import app.techsalaries.api.authentication.UserPrincipal
import app.techsalaries.api.user.model.RefreshTokenRequest
import app.techsalaries.plugins.AUTHENTICATION_STRICT
import app.techsalaries.plugins.controllers
import app.techsalaries.utils.returnResponse
import app.techsalaries.utils.userPrincipal
import io.ktor.application.call
import io.ktor.auth.AuthenticationContext
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Route

fun Route.UserApi() {
    val controller by lazy { controllers.userController() }

    post<TechSalariesApiRoute.Users.Register> {
        returnResponse(controller.register(call.receive()))
    }

    post<TechSalariesApiRoute.Users.Login> {
        returnResponse(controller.login(call.receive()))
    }

    post<TechSalariesApiRoute.Users.RefreshToken> {
        returnResponse(controller.refreshTokenForToken(call.receive<RefreshTokenRequest>().refreshToken))
    }

    authenticate(AUTHENTICATION_STRICT) {
        get<TechSalariesApiRoute.Users.Me> {
            returnResponse(controller.getLoggedInUser(call.authentication.userPrincipal.user))
        }
    }
}

