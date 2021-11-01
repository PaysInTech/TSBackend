package app.techsalaries.db.jobInfo

import app.techsalaries.core.IoDispatcher
import app.techsalaries.core.jobInfo.JobInfoRepository
import app.techsalaries.core.jobInfo.model.ContributionLevel
import app.techsalaries.core.jobInfo.model.JobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.dsl.where
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobInfoRepositoryImpl @Inject constructor(
    private val database: Database,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : JobInfoRepository {

    override suspend fun getAllJobProfiles(): List<JobProfile> = withContext(dispatcher) {
        database
            .from(JobProfiles)
            .select()
            .where { JobProfiles.enabled eq true }
            .map { JobProfile(it[JobProfiles.id]!!, it[JobProfiles.name]!!) }
    }

    override suspend fun getAllProgrammingLanguages(): List<ProgrammingLanguage> = withContext(dispatcher) {
        database
            .from(ProgrammingLanguages)
            .select()
            .where { ProgrammingLanguages.enabled eq true }
            .map { ProgrammingLanguage(it[ProgrammingLanguages.id]!!, it[ProgrammingLanguages.name]!!) }
    }

    override suspend fun getAllTechnologies(): List<Technology> = withContext(dispatcher) {
        database
            .from(Technologies)
            .select()
            .where { Technologies.enabled eq true }
            .map { Technology(it[Technologies.id]!!, it[Technologies.name]!!) }
    }

    override suspend fun getAllContributionLevels(): List<ContributionLevel> = withContext(dispatcher) {
        database
            .from(ContributionLevels)
            .select()
            .where { ContributionLevels.enabled eq true }
            .map { ContributionLevel(it[ContributionLevels.id]!!, it[ContributionLevels.name]!!) }
    }
}
