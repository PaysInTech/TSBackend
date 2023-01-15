package app.paysintech.core.user

import app.paysintech.core.user.model.AddNewUser
import app.paysintech.core.user.model.User
import javax.inject.Singleton

@Singleton
interface UserRepository {
    suspend fun addUser(user: AddNewUser)
    suspend fun findById(id: String): User
    suspend fun findByUsername(username: String): User
}
