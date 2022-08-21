package app.techsalaries.db.user

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = varchar("id", 20)
    val email = varchar("email", 128).uniqueIndex()
    val displayName = varchar("display_name", 256)
    val passwordHash = varchar("password_hash", 64)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id, name = "PK_Users_ID")
}
