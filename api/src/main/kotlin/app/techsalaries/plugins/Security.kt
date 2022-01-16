package app.techsalaries.plugins

import app.techsalaries.api.auth.AuthController
import app.techsalaries.api.sample.SampleController
import dagger.Lazy
import io.ktor.application.Application
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authentication
import io.ktor.auth.basic
import io.ktor.auth.form
import io.ktor.auth.jwt.*

fun Application.configureSecurity(controller: Lazy<AuthController> = controllers.authController()) {


    authentication {
        basic(name = "myauth1") {
            realm = "Ktor Server"
            validate { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }

        form(name = "myauth2") {
            userParamName = "user"
            passwordParamName = "password"
            challenge {
                /**/
            }
        }


        jwt {
            verifier(controller.get().verifyToken())
            realm = controller.get().getRealm()
            validate { jwtCredential ->
                controller.get().getJwtPrincipal(jwtCredential)
            }
        }
    }
}
