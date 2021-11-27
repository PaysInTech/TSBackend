package app.techsalaries.api.info

import app.techsalaries.api.info.model.ContributionLevelsResponse
import app.techsalaries.api.info.model.ContributionLevelsResponse.ContributionLevel
import app.techsalaries.api.info.model.JobProfilesResponse
import app.techsalaries.api.info.model.JobProfilesResponse.JobProfile
import app.techsalaries.api.info.model.ProgrammingLanguagesResponse
import app.techsalaries.api.info.model.ProgrammingLanguagesResponse.ProgrammingLanguage
import app.techsalaries.api.info.model.TechnologiesResponse
import app.techsalaries.api.info.model.TechnologiesResponse.Technology
import app.techsalaries.core.utils.fromJson
import app.techsalaries.utils.TestHttpUtils
import io.kotest.assertions.ktor.shouldHaveStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode

class InfoApiTest : BehaviorSpec({
    Given("The info details") {
        When("Job profiles info is retrieved") {
            val response = TestHttpUtils.get("/api/v1/info/job-profiles").response

            Then("The valid response should be returned") {
                response shouldHaveStatus HttpStatusCode.OK
            }

            Then("Content should be valid") {
                val jobProfilesResponse = fromJson<JobProfilesResponse>(response.content!!)

                jobProfilesResponse.isSuccess shouldBe true
                jobProfilesResponse.profiles shouldContainExactly listOf(
                    JobProfile(1, "Software Engineer"),
                    JobProfile(2, "Product Manager"),
                    JobProfile(3, "QA Engineer"),
                    JobProfile(4, "UI/UX Designer"),
                    JobProfile(5, "Engineering Manager"),
                    JobProfile(6, "Director"),
                    JobProfile(7, "VP"),
                    JobProfile(8, "CXO"),
                )
            }
        }

        When("Technologies info is retrieved") {
            val response = TestHttpUtils.get("/api/v1/info/technologies").response

            Then("The valid response should be returned") {
                response shouldHaveStatus HttpStatusCode.OK
            }

            Then("Content should be valid") {
                val technologiesResponse = fromJson<TechnologiesResponse>(response.content!!)

                technologiesResponse.isSuccess shouldBe true
                technologiesResponse.technologies shouldContainExactly listOf(
                    Technology(1, "Android (Native)"),
                    Technology(2, "iOS (Native)"),
                    Technology(3, "Web"),
                    Technology(4, "Flutter"),
                    Technology(5, "React Native"),
                    Technology(6, "JVM Backend"),
                    Technology(7, "Java"),
                    Technology(8, ".NET"),
                    Technology(9, "Full Stack"),
                    Technology(10, "Rails")
                )
            }
        }

        When("Programming languages info is retrieved") {
            val response = TestHttpUtils.get("/api/v1/info/programming-languages").response

            Then("The valid response should be returned") {
                response shouldHaveStatus HttpStatusCode.OK
            }

            Then("Content should be valid") {
                val contentResponse = fromJson<ProgrammingLanguagesResponse>(response.content!!)

                contentResponse.isSuccess shouldBe true
                contentResponse.programmingLanguages shouldContainExactly listOf(
                    ProgrammingLanguage(1, "Kotlin"),
                    ProgrammingLanguage(2, "Java"),
                    ProgrammingLanguage(3, "Scala"),
                    ProgrammingLanguage(4, "Python"),
                    ProgrammingLanguage(5, "Ruby"),
                    ProgrammingLanguage(6, "Rust"),
                    ProgrammingLanguage(7, "Groovy"),
                    ProgrammingLanguage(8, ".NET"),
                    ProgrammingLanguage(9, "C++"),
                    ProgrammingLanguage(10, "C")
                )
            }
        }

        When("Contribution levels info is retrieved") {
            val response = TestHttpUtils.get("/api/v1/info/contribution-levels").response

            Then("The valid response should be returned") {
                response shouldHaveStatus HttpStatusCode.OK
            }

            Then("Content should be valid") {
                val contentResponse = fromJson<ContributionLevelsResponse>(response.content!!)

                contentResponse.isSuccess shouldBe true
                contentResponse.levels shouldContainExactly listOf(
                    ContributionLevel(1, "Individual Contributor"),
                    ContributionLevel(2, "Team Lead"),
                    ContributionLevel(3, "Manager")
                )
            }
        }
    }
})
