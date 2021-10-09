package app.techsalaries.api.info

import app.techsalaries.api.info.model.ContributionLevelsResponse
import app.techsalaries.api.info.model.JobProfilesResponse
import app.techsalaries.api.info.model.ProgrammingLanguagesResponse
import app.techsalaries.api.info.model.TechnologiesResponse
import app.techsalaries.api.response.HttpResponse
import app.techsalaries.api.response.Success
import app.techsalaries.core.jobInfo.JobInfoService
import app.techsalaries.exception.ServerError
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Controller for [InfoApi].
 */
@Singleton
class InfoController @Inject constructor(private val service: JobInfoService) {
    suspend fun getAllJobProfiles(): HttpResponse<JobProfilesResponse> = try {
        val profiles = service.getAllJobProfiles()
        Success.ok(JobProfilesResponse(profiles = profiles.map { JobProfilesResponse.JobProfile.from(it) }))
    } catch (e: Exception) {
        throw ServerError("Something went wrong")
    }

    suspend fun getAllTechnologies(): HttpResponse<TechnologiesResponse> = try {
        val technologies = service.getAllTechnologies()
        Success.ok(TechnologiesResponse(technologies = technologies.map { TechnologiesResponse.Technology.from(it) }))
    } catch (e: Exception) {
        throw ServerError("Something went wrong")
    }

    suspend fun getAllProgrammingLanguages(): HttpResponse<ProgrammingLanguagesResponse> = try {
        val languages = service.getAllProgrammingLanguages()
        Success.ok(
            ProgrammingLanguagesResponse(languages.map { ProgrammingLanguagesResponse.ProgrammingLanguage.from(it) })
        )
    } catch (e: Exception) {
        throw ServerError("Something went wrong")
    }

    suspend fun getAllContributionLevels(): HttpResponse<ContributionLevelsResponse> = try {
        val levels = service.getAllContributionLevels()
        Success.ok(ContributionLevelsResponse(levels.map { ContributionLevelsResponse.ContributionLevel.from(it) }))
    } catch (e: Exception) {
        throw ServerError("Something went wrong")
    }
}
