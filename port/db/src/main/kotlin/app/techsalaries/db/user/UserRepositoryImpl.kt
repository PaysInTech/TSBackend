package app.techsalaries.db.user

import app.techsalaries.core.user.User
import app.techsalaries.core.user.UserRepository
import app.techsalaries.db.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    override suspend fun user(userId: String, hash: String?): User? {
        val user = dbQuery {
            Users.select { (Users.id eq userId) }.mapNotNull { toUser(it) }.singleOrNull()
        }

        return when {
            user == null -> null
            hash == null -> user
            user.passwordHash == hash -> user
            else -> null
        }
    }

    override suspend fun userByEmail(email: String): User? {
        return dbQuery {
            Users.select { (Users.email eq email) }.mapNotNull { toUser(it) }.singleOrNull()
        }
    }

    override suspend fun userById(userId: String): User? = dbQuery {
        Users.select { Users.id eq userId }
            .map { toUser(it) }
            .singleOrNull()
    }

    override suspend fun createUser(user: User) {
        dbQuery {
            Users.insert {
                it[id] = user.userId
                it[email] = user.email
                it[displayName] = user.displayName
                it[passwordHash] = user.passwordHash
            }
        }
    }

    private fun toUser(row: ResultRow): User = User(
        userId = row[Users.id],
        email = row[Users.email],
        displayName = row[Users.displayName],
        passwordHash = row[Users.passwordHash]
    )
}
