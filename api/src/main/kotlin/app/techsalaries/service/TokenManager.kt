package app.techsalaries.service

import app.techsalaries.api.auth.User
import app.techsalaries.config.TokenConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.config.*
import java.util.*
import javax.inject.Inject

class TokenManager @Inject constructor(tokenConfig: TokenConfig) {

    private val audience = tokenConfig.audience
    private val secret = tokenConfig.secret
    private val issuer = tokenConfig.issuer
    val realm = tokenConfig.realm
    private val expDate: Date
        get() = Date(System.currentTimeMillis() + 600_000)

    fun generateJwtToken(user: User): String? {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.username)
            .withClaim("userId", user.id)
            .withExpiresAt(expDate)
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJwtToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }
}