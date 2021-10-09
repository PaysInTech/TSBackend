package app.techsalaries.api.info.model

import app.techsalaries.api.response.BaseResponse
import kotlinx.serialization.Serializable
import app.techsalaries.core.jobInfo.model.JobProfile as CoreJobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage as CoreProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology as CoreTechnology

@Serializable
class JobProfilesResponse(
    override val isSuccess: Boolean = true,
    val profiles: List<JobProfile>
) : BaseResponse {

    @Serializable
    data class JobProfile(val id: Long, val profileName: String) {
        companion object {
            fun from(profile: CoreJobProfile) = JobProfile(profile.id, profile.name)
        }
    }
}

@Serializable
class TechnologiesResponse(
    override val isSuccess: Boolean = true,
    val technologies: List<Technology>
) : BaseResponse {

    @Serializable
    data class Technology(val id: Long, val name: String) {
        companion object {
            fun from(tech: CoreTechnology) = Technology(tech.id, tech.name)
        }
    }
}

@Serializable
class ProgrammingLanguagesResponse(
    override val isSuccess: Boolean = true,
    val programmingLanguages: List<ProgrammingLanguage>
) : BaseResponse {

    @Serializable
    data class ProgrammingLanguage(val id: Long, val name: String) {
        companion object {
            fun from(language: CoreProgrammingLanguage) = ProgrammingLanguage(language.id, language.name)
        }
    }
}
