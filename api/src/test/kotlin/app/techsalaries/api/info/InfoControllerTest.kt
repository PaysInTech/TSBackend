package app.techsalaries.api.info

import app.techsalaries.api.info.model.JobProfilesResponse
import app.techsalaries.api.info.model.ProgrammingLanguagesResponse
import app.techsalaries.api.info.model.TechnologiesResponse
import app.techsalaries.api.response.Success
import app.techsalaries.core.jobInfo.JobInfoService
import app.techsalaries.core.jobInfo.model.JobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology
import app.techsalaries.exception.ServerError
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class InfoControllerTest : BehaviorSpec({
    val service: JobInfoService = mockk()
    val controller = InfoController(service)

    Given("The job related info") {
        val profiles = listOf(JobProfile(1, "Android Developer"), JobProfile(2, "iOS Developer"))
        val technologies = listOf(Technology(1, "Android"), Technology(2, "Flutter"))
        val programmingLanguages = listOf(ProgrammingLanguage(1, "Kotlin"), ProgrammingLanguage(2, "Java"))

        When("The profiles are retrieved") {
            And("Info retrieved successfully") {
                coEvery { service.getAllJobProfiles() } returns profiles

                val response = controller.getAllJobProfiles()

                Then("Controller should request service") {
                    coVerify(exactly = 1) { service.getAllJobProfiles() }
                }

                Then("Success response should be returned") {
                    response.statusCode shouldBe HttpStatusCode.OK
                    response should beInstanceOf<Success<JobProfilesResponse>>()
                }

                Then("Valid data should be returned") {
                    val data = (response as Success<JobProfilesResponse>).data

                    data.isSuccess shouldBe true
                    data.profiles shouldContainExactly listOf(
                        JobProfilesResponse.JobProfile(1, "Android Developer"),
                        JobProfilesResponse.JobProfile(2, "iOS Developer")
                    )
                }
            }

            And("Info NOT retrieved successfully") {
                coEvery { service.getAllJobProfiles() } throws Exception("")

                Then("Error response should be returned") {
                    shouldThrow<ServerError> { controller.getAllJobProfiles() }
                }
            }
        }

        When("The technologies are retrieved") {
            And("Info retrieved successfully") {
                coEvery { service.getAllTechnologies() } returns technologies

                val response = controller.getAllTechnologies()

                Then("Controller should request service") {
                    coVerify(exactly = 1) { service.getAllTechnologies() }
                }

                Then("Success response should be returned") {
                    response.statusCode shouldBe HttpStatusCode.OK
                    response should beInstanceOf<Success<TechnologiesResponse>>()
                }

                Then("Valid data should be returned") {
                    val data = (response as Success<TechnologiesResponse>).data

                    data.isSuccess shouldBe true
                    data.technologies shouldContainExactly listOf(
                        TechnologiesResponse.Technology(1, "Android"),
                        TechnologiesResponse.Technology(2, "Flutter")
                    )
                }
            }

            And("Info NOT retrieved successfully") {
                coEvery { service.getAllTechnologies() } throws Exception("")

                Then("Error response should be returned") {
                    shouldThrow<ServerError> { controller.getAllTechnologies() }
                }
            }
        }

        When("The programming languages are retrieved") {
            And("Info retrieved successfully") {
                coEvery { service.getAllProgrammingLanguages() } returns programmingLanguages

                val response = controller.getAllProgrammingLanguages()

                Then("Controller should request service") {
                    coVerify(exactly = 1) { service.getAllProgrammingLanguages() }
                }

                Then("Success response should be returned") {
                    response.statusCode shouldBe HttpStatusCode.OK
                    response should beInstanceOf<Success<ProgrammingLanguagesResponse>>()
                }

                Then("Valid data should be returned") {
                    val data = (response as Success<ProgrammingLanguagesResponse>).data

                    data.isSuccess shouldBe true
                    data.programmingLanguages shouldContainExactly listOf(
                        ProgrammingLanguagesResponse.ProgrammingLanguage(1, "Kotlin"),
                        ProgrammingLanguagesResponse.ProgrammingLanguage(2, "Java")
                    )
                }
            }

            And("Info NOT retrieved successfully") {
                coEvery { service.getAllProgrammingLanguages() } throws Exception("")

                Then("Error response should be returned") {
                    shouldThrow<ServerError> { controller.getAllProgrammingLanguages() }
                }
            }
        }
    }
})
