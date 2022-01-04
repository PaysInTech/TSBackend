package app.paysintech.db.user

import app.paysintech.core.IoDispatcher
import app.paysintech.core.exception.errorResourceNotFound
import app.paysintech.core.user.UserRepository
import app.paysintech.core.user.model.AddNewUser
import app.paysintech.core.user.model.User
import app.paysintech.core.user.model.UserRole
import app.paysintech.db.sql.executeTransaction
import app.paysintech.db.user.query.AddUserQuery
import app.paysintech.db.user.query.AddUserRoleCommand
import app.paysintech.db.user.query.FindUserByIdQuery
import app.paysintech.db.user.query.FindUserByUsernameQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

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
