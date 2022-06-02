package app.paysintech.core.user

import app.paysintech.core.user.model.AddNewUser
import app.paysintech.core.user.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val repository: UserRepository) {
    suspend fun addUser(user: AddNewUser) = repository.addUser(user)

    suspend fun findById(id: String): User = repository.findById(id)

    suspend fun findByUsername(username: String): User = repository.findByUsername(username)
}
