package app.techsalaries.api.user

import app.techsalaries.api.authentication.AuthenticatedUser
import app.techsalaries.api.authentication.INVALID_TOKEN
import app.techsalaries.api.authentication.VALID_TOKEN
import app.techsalaries.api.response.UnsuccessfulResponse
import app.techsalaries.api.user.fixtures.userRegistrationRequest
import app.techsalaries.api.user.model.AuthResponse
import app.techsalaries.api.user.model.RefreshTokenRequest
import app.techsalaries.api.user.model.UserLoginRequest
import app.techsalaries.api.user.model.UserResponse
import app.techsalaries.core.user.model.UserRole
import app.techsalaries.core.utils.fromJson
import app.techsalaries.core.utils.json
import app.techsalaries.db.user.query.FindUserByUsernameQuery
import app.techsalaries.testAppComponent
import app.techsalaries.utils.TestHttpUtils
import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.setBody

class UserApiTest : BehaviorSpec({
    Given("New user detail") {
        And("User already exist with same username") {
            val request = userRegistrationRequest().copy(username = AuthenticatedUser.username)

            When("User is registered") {
                val response = TestHttpUtils.post("api/v1/users/register") {
                    setBody(request.json())
                    addHeader("Content-Type", "application/json")
                }.response

                val authResponse = fromJson<UnsuccessfulResponse>(response.content!!)
                Then("Error response should be returned") {
                    response shouldHaveStatus HttpStatusCode.BadRequest
                    authResponse.message shouldBe "User already exists with this username"
                }
            }
        }
        And("User not exists already with same username") {
            val request = userRegistrationRequest()

            When("User is registered") {

                val response = TestHttpUtils.post("api/v1/users/register") {
                    setBody(request.json())
                    addHeader("Content-Type", "application/json")
                }.response

                val authResponse = fromJson<AuthResponse>(response.content!!)
                Then("Success response should be returned") {
                    response shouldHaveStatus HttpStatusCode.OK
                    with(authResponse) {
                        token shouldBe "valid-token"
                        refreshToken shouldBe "refresh-token"
                        expiresIn shouldBe "3600"
                    }
                }

                Then("User detail should be added in the database") {
                    val user = getUserByUsername(request.username)
                    user.email shouldBe request.email
                    user.id shouldBe "example-uuid"
                    user.roles shouldBe listOf(UserRole.MEMBER)
                    user.totalCoins shouldBe 0
                }
            }
        }
    }

    Given("User credentials for login") {
        And("Credentials are wrong") {
            val request = UserLoginRequest(AuthenticatedUser.username, "invalid-password")

            When("User signs in") {
                val response = TestHttpUtils.post("api/v1/users/login") {
                    setBody(request.json())
                    addHeader("Content-Type", "application/json")
                }.response

                val authResponse = fromJson<UnsuccessfulResponse>(response.content!!)
                Then("Error response should be returned") {
                    response shouldHaveStatus HttpStatusCode.Unauthorized
                    authResponse.message shouldBe "Authentication Failed. Invalid credentials provided."
                }
            }
        }
        And("Credentials are correct") {
            val request = UserLoginRequest(AuthenticatedUser.username, "valid-password")

            When("User signs in") {
                val response = TestHttpUtils.post("api/v1/users/login") {
                    setBody(request.json())
                    addHeader("Content-Type", "application/json")
                }.response

                val authResponse = fromJson<AuthResponse>(response.content!!)

                Then("Success response should be returned") {
                    response shouldHaveStatus HttpStatusCode.OK
                    authResponse.token shouldBe "valid-token"
                    authResponse.refreshToken shouldBe "refresh-token"
                    authResponse.expiresIn shouldBe "3600"
                }
            }
        }
    }

    Given("A user token") {
        And("Token is valid") {
            val request = RefreshTokenRequest(VALID_TOKEN)

            When("Token is refreshed") {
                val response = TestHttpUtils.post("api/v1/users/token/refresh") {
                    setBody(request.json())
                    addHeader("Content-Type", "application/json")
                }.response

                val authResponse = fromJson<AuthResponse>(response.content!!)

                Then("Success response should be returned") {
                    response shouldHaveStatus HttpStatusCode.OK
                    authResponse.token shouldBe "valid-token"
                    authResponse.refreshToken shouldBe "refresh-token"
                    authResponse.expiresIn shouldBe "3600"
                }
            }

            When("User details are retrieved") {
                val response = TestHttpUtils.get("api/v1/users/me") {
                    setBody(request.json())
                    addHeader("Authorization", VALID_TOKEN)
                    addHeader("Content-Type", "application/json")
                }.response

                val userResponse = fromJson<UserResponse>(response.content!!)

                Then("Success response should be returned") {
                    response shouldHaveStatus HttpStatusCode.OK
                    with(userResponse) {
                        username shouldBe "user1234"
                        email shouldBe "example@email.com"
                        totalCoins shouldBe 1000
                        roles shouldBe listOf("Member")
                    }
                }
            }
        }
        And("Token is invalid") {
            val request = RefreshTokenRequest(INVALID_TOKEN)

            When("Token is refreshed") {
                val response = TestHttpUtils.post("api/v1/users/token/refresh") {
                    setBody(request.json())
                    addHeader("Content-Type", "application/json")
                }.response

                val authResponse = fromJson<UnsuccessfulResponse>(response.content!!)

                Then("Error response should be returned") {
                    response shouldHaveStatus HttpStatusCode.Unauthorized
                    authResponse.message shouldBe "Invalid refresh token"
                }
            }

            When("User details are retrieved") {
                val response = TestHttpUtils.get("api/v1/users/me") {
                    setBody(request.json())
                    addHeader("Authorization", INVALID_TOKEN)
                    addHeader("Content-Type", "application/json")
                }.response

                val userResponse = fromJson<UnsuccessfulResponse>(response.content!!)

                Then("Success response should be returned") {
                    response shouldHaveStatus HttpStatusCode.Unauthorized
                    userResponse.message shouldBe "Invalid authentication token!"
                }
            }
        }
    }
})

fun getUserByUsername(username: String) =
    FindUserByUsernameQuery().executeAsOne(testAppComponent.dataSource(), username)
