package app.paysintech.api.info

import app.paysintech.api.info.model.ContributionLevelsResponse
import app.paysintech.api.info.model.JobProfilesResponse
import app.paysintech.api.info.model.ProgrammingLanguagesResponse
import app.paysintech.api.info.model.TechnologiesResponse
import app.paysintech.api.response.HttpResponse
import app.paysintech.api.response.Success
import app.paysintech.core.jobInfo.JobInfoService
import app.paysintech.utils.handleResponse
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
