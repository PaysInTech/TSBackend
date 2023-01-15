package app.paysintech.db.jobInfo

import app.paysintech.core.IoDispatcher
import app.paysintech.core.jobInfo.JobInfoRepository
import app.paysintech.core.jobInfo.model.ContributionLevel
import app.paysintech.core.jobInfo.model.JobProfile
import app.paysintech.core.jobInfo.model.ProgrammingLanguage
import app.paysintech.core.jobInfo.model.Technology
import app.paysintech.db.jobInfo.query.GetAllContributionLevels
import app.paysintech.db.jobInfo.query.GetAllJobProfilesQuery
import app.paysintech.db.jobInfo.query.GetAllProgrammingLanguages
import app.paysintech.db.jobInfo.query.GetAllTechnologies
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
