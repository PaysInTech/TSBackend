package app.techsalaries.api.auth


import app.techsalaries.service.TokenManager
import com.auth0.jwt.JWTVerifier
import io.ktor.auth.jwt.*
import org.ktorm.database.Database
import javax.inject.Inject

class AuthController @Inject constructor(private val tokenManager: TokenManager, private val database: Database) {

    suspend fun checkUserExists(user: User): User? {
        TODO("Not yet implemented")
    }

    suspend fun insertUser(user: User): Int {
        TODO("Not yet implemented")
    }

    suspend fun validatePassword(password: String?, savedPassword: String?): Boolean {
        TODO("Not yet implemented")
    }

    suspend fun generateToken(user: User): String? {
        return tokenManager.generateJwtToken(user)
    }

    suspend fun getJwtPrincipal(jwtCredential: JWTCredential): JWTPrincipal? {
        val username = jwtCredential.payload.getClaim("username").asString()
        return if (username.isNotEmpty()) {
            val user = checkUserExists(
                user = User(
                    username = username,
                    password = ""
                )
            )
            if (user != null) {
                JWTPrincipal(jwtCredential.payload)
            } else {
                null
            }
        } else {
            null
        }
    }

    fun verifyToken(): JWTVerifier {
        return tokenManager.verifyJwtToken()
    }

    fun getRealm(): String {
        return tokenManager.realm
    }
}