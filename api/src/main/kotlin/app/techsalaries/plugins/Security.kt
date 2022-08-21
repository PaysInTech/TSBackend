package app.techsalaries.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt
import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

const val MIN_USER_ID_LENGTH = 4
const val MIN_PASSWORD_LENGTH = 6

val hashKey = hex("80900183AB")

val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")

fun hash(password: String): String {
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmacKey)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}

private val userIdPattern = "[a-zA-Z0-9_\\.]+".toRegex()

internal fun userNameValid(userId: String) = userId.matches(userIdPattern)

fun Application.configureSecurity() {

    val jwtService = appComponent.jwtService()
    val userController = controllers.userController().get()

    install(Authentication) {
        jwt("jwt") {
            verifier(jwtService.verifier)
            realm = "paysintech app"
            validate {
                val payload = it.payload
                val claim = payload.getClaim("id")
                val claimString = claim.asString()
                val user = userController.userById(claimString)
                user
            }
        }
    }
}
