package app.techsalaries.api.user

import ApiErrorMessage
import app.techsalaries.JwtService
import app.techsalaries.api.API_PREFIX
import app.techsalaries.api.API_VERSION
import app.techsalaries.core.user.User
import app.techsalaries.model.AccessToken
import app.techsalaries.plugins.MIN_PASSWORD_LENGTH
import app.techsalaries.plugins.MIN_USER_ID_LENGTH
import app.techsalaries.plugins.controllers
import app.techsalaries.plugins.userNameValid
import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.application.application
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.server.request.receiveParameters
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable

const val SIGN_UP_API = "$API_PREFIX/$API_VERSION/signup"

@Serializable
@Resource(SIGN_UP_API)
class SignUp

fun Route.signup(
    userController: UserController = controllers.userController().get(),
    jwtService: JwtService,
    hashFunction: (String) -> String,
) {

    post<SignUp> {
        val signUpParameters = call.receiveParameters()
        val userId = signUpParameters["userId"] ?: return@post call.respond(status = HttpStatusCode.BadRequest, message = ApiErrorMessage("userId missing"))
        val password = signUpParameters["password"] ?: return@post call.respond(status = HttpStatusCode.BadRequest, message = ApiErrorMessage("password missing"))
        val displayName = signUpParameters["displayName"] ?: return@post call.respond(status = HttpStatusCode.BadRequest, message = ApiErrorMessage("displayName missing"))
        val email = signUpParameters["email"] ?: return@post call.respond(status = HttpStatusCode.BadRequest, message = ApiErrorMessage("email missing"))

        when {
            password.length < MIN_PASSWORD_LENGTH -> call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiErrorMessage("Password should be at least $MIN_PASSWORD_LENGTH characters long")
            )

            userId.length < MIN_USER_ID_LENGTH -> call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiErrorMessage("User should be at least $MIN_USER_ID_LENGTH characters long")
            )

            !userNameValid(userId) -> call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiErrorMessage("Username should consist of digits, letters, dots or underscores")
            )
            userController.user(userId) != null -> call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiErrorMessage("User with the following username is already registered")
            )
            else -> {
                val hash = hashFunction(password)
                val newUser = User(userId, email, displayName, hash)

                try {
                    userController.createUser(newUser)
                    val token = jwtService.generateToken(newUser)
                    call.respond(status = HttpStatusCode.OK, message = AccessToken(token))
                } catch (e: Exception) {
                    when {
                        userController.user(userId) != null -> call.respond(status = HttpStatusCode.BadRequest, message = ApiErrorMessage("User with the following username is already registered"))

                        userController.userByEmail(email) != null -> call.respond(status = HttpStatusCode.BadRequest, message = ApiErrorMessage("User with the following email $email is already registered"))

                        else -> {
                            application.log.error("Failed to register the user", e)
                            call.respond(status = HttpStatusCode.NotAcceptable, message = ApiErrorMessage("Failed to register"))
                        }
                    }
                }
            }
        }
    }
}
