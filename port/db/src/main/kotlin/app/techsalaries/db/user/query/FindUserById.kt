package app.techsalaries.db.user.query

import app.techsalaries.core.user.model.User
import app.techsalaries.core.user.model.UserRole
import app.techsalaries.db.columnAdapter.StringArrayAdapter
import app.techsalaries.db.sql.Query
import java.sql.PreparedStatement

class FindUserByIdQuery : Query<User, String>() {
    //language=sql
    override val sql: String = """
        SELECT u.id                             AS user_id,
               u.username,
               u.email,
               string_agg(DISTINCT r.name, ',') AS roles,
               sum(DISTINCT s.coins) AS total_coins
        FROM users u
                 INNER JOIN user_roles ur ON u.id = ur.user_id
                 INNER JOIN roles r ON r.id = ur.role_id
                 LEFT JOIN salaries s ON u.id = s.user_id
        WHERE u.id = ?
        GROUP BY u.id
        LIMIT 1
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: String) {
        statement.setString(1, param)
    }

    override fun mapResult(result: Map<String, Any?>): User = User(
        id = result["user_id"] as String,
        username = result["username"] as String,
        email = result["email"] as String,
        totalCoins = result["total_coins"] as? Long ?: 0,
        roles = StringArrayAdapter.decode(result["roles"] as String).mapNotNull { UserRole.byRoleName(it) }
    )
}
