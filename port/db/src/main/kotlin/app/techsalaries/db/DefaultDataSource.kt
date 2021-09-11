package app.techsalaries.db

import com.zaxxer.hikari.HikariDataSource

class DefaultDataSource(databaseUrl: String, databaseUser: String, databasePassword: String) : HikariDataSource() {
    init {
        jdbcUrl = databaseUrl
        username = databaseUser
        password = databasePassword
    }
}
