package app.techsalaries.db.user.query

import app.techsalaries.core.user.model.AddNewUser
import app.techsalaries.db.sql.Query
import java.sql.PreparedStatement

class AddUserQuery : Query<String, AddNewUser>() {
    //language=sql
    override val sql: String = """
        INSERT INTO users(id, username, email) VALUES (?, ?, ?)
        RETURNING id
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: AddNewUser) = with(statement) {
        setString(1, param.uid)
        setString(2, param.username)
        setString(3, param.email)
    }

    override fun mapResult(result: Map<String, Any?>): String = result["id"] as String
}