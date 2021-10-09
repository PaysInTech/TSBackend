package app.techsalaries.core.jobInfo

import app.techsalaries.core.jobInfo.model.JobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology

/**
 * Source of truth for Job Info related data
 */
interface JobInfoRepository {
    suspend fun getAllJobProfiles(): List<JobProfile>
    suspend fun getAllProgrammingLanguages(): List<ProgrammingLanguage>
    suspend fun getAllTechnologies(): List<Technology>
}
