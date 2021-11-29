package app.techsalaries.api.info

import app.techsalaries.api.info.model.ContributionLevelsResponse
import app.techsalaries.api.info.model.JobProfilesResponse
import app.techsalaries.api.info.model.ProgrammingLanguagesResponse
import app.techsalaries.api.info.model.TechnologiesResponse
import app.techsalaries.api.response.HttpResponse
import app.techsalaries.api.response.Success
import app.techsalaries.core.jobInfo.JobInfoService
import app.techsalaries.utils.handleResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Controller for [InfoApi].
 */
@Singleton
class InfoController @Inject constructor(private val service: JobInfoService) {
    suspend fun getAllJobProfiles(): HttpResponse<JobProfilesResponse> = handleResponse {
        val profiles = service.getAllJobProfiles()
        Success.ok(JobProfilesResponse(profiles = profiles.map { JobProfilesResponse.JobProfile.from(it) }))
    }

    suspend fun getAllTechnologies(): HttpResponse<TechnologiesResponse> = handleResponse {
        val technologies = service.getAllTechnologies()
        Success.ok(TechnologiesResponse(technologies = technologies.map { TechnologiesResponse.Technology.from(it) }))
    }

    suspend fun getAllProgrammingLanguages(): HttpResponse<ProgrammingLanguagesResponse> = handleResponse {
        val languages = service.getAllProgrammingLanguages()
        Success.ok(
            ProgrammingLanguagesResponse(languages.map { ProgrammingLanguagesResponse.ProgrammingLanguage.from(it) })
        )
    }

    suspend fun getAllContributionLevels(): HttpResponse<ContributionLevelsResponse> = handleResponse {
        val levels = service.getAllContributionLevels()
        Success.ok(ContributionLevelsResponse(levels.map { ContributionLevelsResponse.ContributionLevel.from(it) }))
    }
}
