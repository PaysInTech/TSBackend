package app.techsalaries.api.user

import app.techsalaries.core.user.User
import app.techsalaries.core.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserController @Inject constructor(private val userRepo: UserRepository) {

    suspend fun user(userId: String, hash: String? = null): User? = userRepo.user(userId, hash)
    suspend fun userByEmail(email: String): User? = userRepo.userByEmail(email)
    suspend fun userById(userId: String): User? = userRepo.userById(userId)
    suspend fun createUser(user: User) = userRepo.createUser(user)
}
