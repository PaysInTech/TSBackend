package app.paysintech.db.user.query

import app.paysintech.core.user.model.UserRole
import app.paysintech.db.sql.Command
import java.sql.PreparedStatement

class AddUserRoleCommand : Command<Pair<String, UserRole>>() {

    //language=sql
    override val sql: String = """
        INSERT INTO user_roles(user_id, role_id) VALUES (?, (SELECT id FROM roles WHERE name = ? LIMIT 1))
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: Pair<String, UserRole>) = with(statement) {
        val (userId, role) = param
        setString(1, userId)
        setString(2, role.roleName)
    }
}
