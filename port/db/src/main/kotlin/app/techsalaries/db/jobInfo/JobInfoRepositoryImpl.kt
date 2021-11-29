package app.techsalaries.db.jobInfo

import app.techsalaries.core.IoDispatcher
import app.techsalaries.core.jobInfo.JobInfoRepository
import app.techsalaries.core.jobInfo.model.ContributionLevel
import app.techsalaries.core.jobInfo.model.JobProfile
import app.techsalaries.core.jobInfo.model.ProgrammingLanguage
import app.techsalaries.core.jobInfo.model.Technology
import app.techsalaries.db.jobInfo.query.GetAllContributionLevels
import app.techsalaries.db.jobInfo.query.GetAllJobProfilesQuery
import app.techsalaries.db.jobInfo.query.GetAllProgrammingLanguages
import app.techsalaries.db.jobInfo.query.GetAllTechnologies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class JobInfoRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : JobInfoRepository {

    override suspend fun getAllJobProfiles(): List<JobProfile> = withContext(dispatcher) {
        GetAllJobProfilesQuery().executeAsList(dataSource, Unit)
    }

    override suspend fun getAllProgrammingLanguages(): List<ProgrammingLanguage> = withContext(dispatcher) {
        GetAllProgrammingLanguages().executeAsList(dataSource, Unit)
    }

    override suspend fun getAllTechnologies(): List<Technology> = withContext(dispatcher) {
        GetAllTechnologies().executeAsList(dataSource, Unit)
    }

    override suspend fun getAllContributionLevels(): List<ContributionLevel> = withContext(dispatcher) {
        GetAllContributionLevels().executeAsList(dataSource, Unit)
    }
}
