package app.paysintech.api.salary

import app.paysintech.api.authentication.AuthenticatedUser
import app.paysintech.api.authentication.INVALID_TOKEN
import app.paysintech.api.authentication.VALID_TOKEN
import app.paysintech.api.response.UnsuccessfulResponse
import app.paysintech.api.salary.fixtures.addSalaryDetailRequest
import app.paysintech.api.salary.model.AddSalaryDetailResponse
import app.paysintech.core.salary.model.Metadata
import app.paysintech.core.utils.fromJson
import app.paysintech.core.utils.json
import app.paysintech.db.utils.sql.query
import app.paysintech.testAppComponent
import app.paysintech.utils.TestHttpUtils
import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThanOrEqualTo
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.setBody

class SalaryApiTest : BehaviorSpec({
    Given("Salary details") {
        val request = addSalaryDetailRequest()

        When("Salary details are posted") {
            val response = TestHttpUtils.post("api/v1/salaries") {
                setBody(request.json())
                addHeader("Content-Type", "application/json")
            }.response

            Then("OK response should be returned") {
                response shouldHaveStatus HttpStatusCode.OK
            }

            val addSalaryResponse = fromJson<AddSalaryDetailResponse>(response.content!!)
            Then("Valid response should be returned with ID and earned coin details") {
                with(addSalaryResponse) {
                    id shouldBeGreaterThanOrEqualTo 1
                    coinsEarned shouldBe 7100
                }
            }

            Then("Salary details should be get added in the database") {
                val salaryDetails = getSalaryDetail(addSalaryResponse.id).first()

                salaryDetails["coins"] shouldBe 7100
                salaryDetails["currency"] shouldBe "INR"
                salaryDetails["user_id"] shouldBe null
                fromJson<Metadata>(salaryDetails["metadata"].toString()).let {
                    it.ip shouldBe "localhost"
                    it.location shouldBe null
                }
                salaryDetails["username"] shouldBe null
                salaryDetails["email"] shouldBe null
                salaryDetails["company_name"] shouldBe "Example Technologies"
                salaryDetails["designation"] shouldBe "Software Engineer"
                salaryDetails["location_state"] shouldBe "Maharashtra"
                salaryDetails["location_city"] shouldBe "Mumbai"
                salaryDetails["profile_id"] shouldBe 1
                salaryDetails["contribution_level_id"] shouldBe 1
                salaryDetails["level"] shouldBe "L1"
                salaryDetails["team"] shouldBe "Development"
                salaryDetails["annual_ctc"].toString() shouldBe "100000"
                salaryDetails["in_hand_per_month"].toString() shouldBe "8000"
                salaryDetails["bonus"].toString() shouldBe "5000"
                salaryDetails["joining_date"].toString() shouldBe "2021-11-11"
                salaryDetails["last_working_date"] shouldBe null
                salaryDetails["is_promoted"] shouldBe false
                salaryDetails["work_type"] shouldBe "FULL_REMOTE"
            }
        }
    }

    Given("Salary details with user authentication") {
        val request = addSalaryDetailRequest()

        When("Salary details are posted") {
            val response = TestHttpUtils.post("api/v1/salaries") {
                setBody(request.json())
                addHeader("Authorization", VALID_TOKEN)
                addHeader("Content-Type", "application/json")
            }.response

            val addSalaryResponse = fromJson<AddSalaryDetailResponse>(response.content!!)
            Then("Valid response should be returned with ID and earned coin details") {
                with(addSalaryResponse) {
                    id shouldBeGreaterThanOrEqualTo 1
                    coinsEarned shouldBe 8100
                }
            }

            Then("Salary details should be get added in the database") {
                val salaryDetails = getSalaryDetail(addSalaryResponse.id).first()

                salaryDetails["coins"] shouldBe 8100
                salaryDetails["currency"] shouldBe "INR"
                salaryDetails["user_id"] shouldBe AuthenticatedUser.id
                fromJson<Metadata>(salaryDetails["metadata"].toString()).let {
                    it.ip shouldBe "localhost"
                    it.location shouldBe null
                }
                salaryDetails["username"] shouldBe AuthenticatedUser.username
                salaryDetails["email"] shouldBe AuthenticatedUser.email
                salaryDetails["company_name"] shouldBe "Example Technologies"
                salaryDetails["designation"] shouldBe "Software Engineer"
                salaryDetails["location_state"] shouldBe "Maharashtra"
                salaryDetails["location_city"] shouldBe "Mumbai"
                salaryDetails["profile_id"] shouldBe 1
                salaryDetails["contribution_level_id"] shouldBe 1
                salaryDetails["level"] shouldBe "L1"
                salaryDetails["team"] shouldBe "Development"
                salaryDetails["annual_ctc"].toString() shouldBe "100000"
                salaryDetails["in_hand_per_month"].toString() shouldBe "8000"
                salaryDetails["bonus"].toString() shouldBe "5000"
                salaryDetails["joining_date"].toString() shouldBe "2021-11-11"
                salaryDetails["last_working_date"] shouldBe null
                salaryDetails["is_promoted"] shouldBe false
                salaryDetails["work_type"] shouldBe "FULL_REMOTE"
            }
        }
    }

    Given("Salary details with unauthenticated user") {
        val request = addSalaryDetailRequest()

        When("Salary details are posted") {
            val response = TestHttpUtils.post("api/v1/salaries") {
                setBody(request.json())
                addHeader("Authorization", INVALID_TOKEN)
                addHeader("Content-Type", "application/json")
            }.response

            val errorResponse = fromJson<UnsuccessfulResponse>(response.content!!)
            Then("Unauthenticated response should be sent") {
                response shouldHaveStatus HttpStatusCode.Unauthorized
                errorResponse.message shouldBe "Invalid authentication token!"
            }
        }
    }
})

//language=sql
fun getSalaryDetail(id: Long) = testAppComponent.dataSource().query(
    """
            SELECT * FROM salaries s 
            LEFT JOIN users u ON s.user_id = u.id 
            INNER JOIN job_details jd ON jd.id = s.job_detail_id
            WHERE s.id = ?
    """.trimIndent(),
    params = listOf(id)
)
