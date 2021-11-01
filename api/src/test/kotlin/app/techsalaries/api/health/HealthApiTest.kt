package app.techsalaries.api.health

import app.techsalaries.utils.TestHttpUtils
import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.ktor.http.HttpStatusCode

class HealthApiTest : BehaviorSpec({
    Given("Application dependencies") {
        When("Health is checked") {
            val response = TestHttpUtils.get("api/v1/health").response

            Then("OK response should be returned") {
                response shouldHaveStatus HttpStatusCode.OK
            }
        }
    }
})
