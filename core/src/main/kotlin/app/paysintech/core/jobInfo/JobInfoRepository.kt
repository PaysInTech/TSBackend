package app.paysintech.core.jobInfo

import app.paysintech.core.jobInfo.model.ContributionLevel
import app.paysintech.core.jobInfo.model.JobProfile
import app.paysintech.core.jobInfo.model.ProgrammingLanguage
import app.paysintech.core.jobInfo.model.Technology

/**
 * Source of truth for Job Info related data
 */
interface JobInfoRepository {
    suspend fun getAllJobProfiles(): List<JobProfile>
    suspend fun getAllProgrammingLanguages(): List<ProgrammingLanguage>
    suspend fun getAllTechnologies(): List<Technology>
    suspend fun getAllContributionLevels(): List<ContributionLevel>
}
