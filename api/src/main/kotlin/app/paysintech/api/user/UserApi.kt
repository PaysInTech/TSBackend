package app.paysintech.api.user

import app.paysintech.api.PaysInTechApiRoute
import app.paysintech.api.user.model.RefreshTokenRequest
import app.paysintech.plugins.AUTHENTICATION_STRICT
import app.paysintech.plugins.controllers
import app.paysintech.utils.returnResponse
import app.paysintech.utils.userPrincipal
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Route

fun Route.UserApi() {
    val controller by lazy { controllers.userController() }

    post<PaysInTechApiRoute.Users.Register> {
        returnResponse(controller.register(call.receive()))
    }

    post<PaysInTechApiRoute.Users.Login> {
        returnResponse(controller.login(call.receive()))
    }

    post<PaysInTechApiRoute.Users.RefreshToken> {
        returnResponse(controller.refreshTokenForToken(call.receive<RefreshTokenRequest>().refreshToken))
    }

    authenticate(AUTHENTICATION_STRICT) {
        get<PaysInTechApiRoute.Users.Me> {
            returnResponse(controller.getLoggedInUser(call.authentication.userPrincipal.user))
        }
    }
}
