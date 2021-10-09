package app.techsalaries.api.info.model

import app.techsalaries.core.jobInfo.model.JobProfile as CoreJobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage as CoreProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology as CoreTechnology
import app.techsalaries.api.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class JobProfilesResponse(val profiles: List<JobProfile>) : BaseResponse(isSuccess = true) {

    @Serializable
    data class JobProfile(val id: Long, val profileName: String) {
        companion object {
            fun from(profile: CoreJobProfile) = JobProfile(profile.id, profile.name)
        }
    }
}

@Serializable
data class TechnologiesResponse(val technologies: List<Technology>) : BaseResponse(isSuccess = true) {

    @Serializable
    data class Technology(val id: Long, val name: String) {
        companion object {
            fun from(tech: CoreTechnology) = Technology(tech.id, tech.name)
        }
    }
}

@Serializable
data class ProgrammingLanguagesResponse(
    val programmingLanguages: List<ProgrammingLanguage>
) : BaseResponse(isSuccess = true) {

    @Serializable
    data class ProgrammingLanguage(val id: Long, val name: String) {
        companion object {
            fun from(language: CoreProgrammingLanguage) = ProgrammingLanguage(language.id, language.name)
        }
    }
}
