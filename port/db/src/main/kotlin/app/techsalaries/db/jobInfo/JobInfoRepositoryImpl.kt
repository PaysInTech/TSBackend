package app.techsalaries.db.jobInfo

import app.techsalaries.core.IoDispatcher
import app.techsalaries.core.jobInfo.JobInfoRepository
import app.techsalaries.core.jobInfo.model.ContributionLevel
import app.techsalaries.core.jobInfo.model.JobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology
import app.techsalaries.db.schema.ContributionLevels
import app.techsalaries.db.schema.JobProfiles
import app.techsalaries.db.schema.ProgrammingLanguages
import app.techsalaries.db.schema.Technologies
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
            .map { JobProfiles.createEntity(it) }
            .map { JobProfile(it.id, it.name) }
    }

    override suspend fun getAllProgrammingLanguages(): List<ProgrammingLanguage> = withContext(dispatcher) {
        database
            .from(ProgrammingLanguages)
            .select()
            .where { ProgrammingLanguages.enabled eq true }
            .map { ProgrammingLanguages.createEntity(it) }
            .map { ProgrammingLanguage(it.id, it.name) }
    }

    override suspend fun getAllTechnologies(): List<Technology> = withContext(dispatcher) {
        database
            .from(Technologies)
            .select()
            .where { Technologies.enabled eq true }
            .map { Technologies.createEntity(it) }
            .map { Technology(it.id, it.name) }
    }

    override suspend fun getAllContributionLevels(): List<ContributionLevel> = withContext(dispatcher) {
        database
            .from(ContributionLevels)
            .select()
            .where { ContributionLevels.enabled eq true }
            .map { ContributionLevels.createEntity(it) }
            .map { ContributionLevel(it.id, it.name) }
    }
}
