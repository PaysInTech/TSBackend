package app.techsalaries.core.user

import app.techsalaries.core.user.model.AddNewUser
import app.techsalaries.core.user.model.User
import javax.inject.Singleton

@Singleton
interface UserRepository {
    suspend fun addUser(user: AddNewUser)
    suspend fun findById(id: String): User
    suspend fun findByUsername(username: String): User
}