package app.techsalaries.testData

import app.techsalaries.api.authentication.AuthenticatedUser
import app.techsalaries.core.user.model.AddNewUser
import app.techsalaries.core.user.model.UserRole
import app.techsalaries.db.user.query.AddUserQuery
import app.techsalaries.db.user.query.AddUserRoleCommand
import javax.sql.DataSource

fun setupTestData(dataSource: DataSource) {
    setupUser(dataSource)
}

/**
 * Sets up test user data for authentication
 */
fun setupUser(dataSource: DataSource) {
    val userId = AddUserQuery().executeAsOne(
        dataSource,
        AuthenticatedUser.let { AddNewUser(it.id, it.username, it.email) }
    )
    AddUserRoleCommand().execute(dataSource, userId to UserRole.MEMBER)
}
