package app.paysintech.api.info.model

import app.paysintech.api.response.BaseResponse
import kotlinx.serialization.Serializable
import app.paysintech.core.jobInfo.model.ContributionLevel as CoreContributionLevel
import app.paysintech.core.jobInfo.model.JobProfile as CoreJobProfile
import app.paysintech.core.jobInfo.model.ProgrammingLanguage as CoreProgrammingLanguage
import app.paysintech.core.jobInfo.model.Technology as CoreTechnology

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

@Serializable
data class ContributionLevelsResponse(val levels: List<ContributionLevel>) : BaseResponse(isSuccess = true) {

    @Serializable
    data class ContributionLevel(val id: Long, val level: String) {
        companion object {
            fun from(level: CoreContributionLevel) = ContributionLevel(level.id, level.name)
        }
    }
}
