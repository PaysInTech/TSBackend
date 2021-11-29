package app.techsalaries.core.user

import app.techsalaries.core.user.model.AddNewUser
import app.techsalaries.core.user.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val repository: UserRepository) {
    suspend fun addUser(user: AddNewUser) = repository.addUser(user)

    suspend fun findById(id: String): User = repository.findById(id)

    suspend fun findByUsername(username: String): User = repository.findByUsername(username)
}
