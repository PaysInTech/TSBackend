package app.techsalaries.db.user

import app.techsalaries.core.IoDispatcher
import app.techsalaries.core.exception.errorResourceNotFound
import app.techsalaries.core.user.UserRepository
import app.techsalaries.core.user.model.AddNewUser
import app.techsalaries.core.user.model.User
import app.techsalaries.core.user.model.UserRole
import app.techsalaries.db.sql.executeTransaction
import app.techsalaries.db.user.query.AddUserQuery
import app.techsalaries.db.user.query.AddUserRoleCommand
import app.techsalaries.db.user.query.FindUserByIdQuery
import app.techsalaries.db.user.query.FindUserByUsernameQuery
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun addUser(user: AddNewUser): Unit = withContext(dispatcher) {
        dataSource.executeTransaction { connection ->
            val userId = AddUserQuery().executeAsOne(connection, user)
            // For now, directly assign member role by default
            AddUserRoleCommand().execute(connection, userId to UserRole.MEMBER)
        }.get()
    }

    override suspend fun findById(id: String): User = withContext(dispatcher) {
        FindUserByIdQuery().executeAsOneOrNull(dataSource, id) ?: errorResourceNotFound("User not exists")
    }

    override suspend fun findByUsername(username: String): User = withContext(dispatcher) {
        FindUserByUsernameQuery().executeAsOneOrNull(dataSource, username) ?: errorResourceNotFound("User not exists")
    }

}