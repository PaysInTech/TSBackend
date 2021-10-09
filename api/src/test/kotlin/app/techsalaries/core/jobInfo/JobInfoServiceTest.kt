package app.techsalaries.core.jobInfo

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class JobInfoServiceTest : BehaviorSpec({
    val repository: JobInfoRepository = mockk()
    val service = JobInfoService(repository)

    Given("The job related info") {
        coEvery { repository.getAllJobProfiles() } returns emptyList()
        coEvery { repository.getAllTechnologies() } returns emptyList()
        coEvery { repository.getAllProgrammingLanguages() } returns emptyList()

        When("The profiles info is requested") {

            service.getAllJobProfiles()

            Then("Service should request repository for data") {
                coVerify(exactly = 1) { repository.getAllJobProfiles() }
            }
        }

        When("The technologies info is requested") {

            service.getAllTechnologies()

            Then("Service should request repository for data") {
                coVerify(exactly = 1) { repository.getAllTechnologies() }
            }
        }

        When("The programming languages info is requested") {

            service.getAllProgrammingLanguages()

            Then("Service should request repository for data") {
                coVerify(exactly = 1) { repository.getAllProgrammingLanguages() }
            }
        }
    }
})
