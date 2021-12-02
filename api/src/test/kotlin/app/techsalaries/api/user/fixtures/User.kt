package app.techsalaries.api.user.fixtures

import app.techsalaries.api.user.model.UserRegistrationRequest
import app.techsalaries.http.firebase.authentication.model.AuthenticatedUser

fun userRegistrationRequest() = UserRegistrationRequest("testuser1234", "example@example.com", "test1234")

fun authenticatedUser() = AuthenticatedUser("example-uuid", "testuser1234", "example@example.com", false)
