package app.paysintech.testData

import app.paysintech.api.authentication.AuthenticatedUser
import app.paysintech.core.user.model.AddNewUser
import app.paysintech.core.user.model.UserRole
import app.paysintech.db.user.query.AddUserQuery
import app.paysintech.db.user.query.AddUserRoleCommand
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
