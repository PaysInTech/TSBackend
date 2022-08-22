package app.techsalaries.db

import app.techsalaries.db.user.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(databaseUrl: String, databaseUser: String, databasePassword: String): Database {
        return Database.connect(hikari(databaseUrl, databaseUser, databasePassword)).apply {
            transaction {
                SchemaUtils.create(Users)
            }
        }
    }

    private fun hikari(databaseUrl: String, databaseUser: String, databasePassword: String): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = databaseUrl
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            username = databaseUser
            password = databasePassword
            validate()
        }

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}
