package app.techsalaries

import app.techsalaries.core.user.User
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date
import javax.inject.Singleton

@Singleton
class JwtService {
    private val issuer = "paysintech"
    private val jwtSecret = "9809ABH23"
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.userId)
        .withExpiresAt(expiresAt())
        .sign(algorithm)

    private fun expiresAt() = Date(System.currentTimeMillis() + 3_600_000 * 24)
}
