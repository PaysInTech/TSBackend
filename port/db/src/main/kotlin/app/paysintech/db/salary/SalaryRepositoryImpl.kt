package app.paysintech.db.salary

import app.paysintech.core.IoDispatcher
import app.paysintech.core.salary.SalaryRepository
import app.paysintech.core.salary.model.AddSalary
import app.paysintech.core.salary.model.AddSalaryDetail
import app.paysintech.core.salary.model.AdditionalBenefits
import app.paysintech.db.jobInfo.query.GetAllContributionLevels
import app.paysintech.db.jobInfo.query.GetProgrammingLanguagesByIdsQuery
import app.paysintech.db.jobInfo.query.GetTechnologiesByIdsQuery
import app.paysintech.db.salary.query.AddJobBenefitCommand
import app.paysintech.db.salary.query.AddJobDetailsQuery
import app.paysintech.db.salary.query.AddJobProgrammingLanguageCommand
import app.paysintech.db.salary.query.AddJobTechnologyCommand
import app.paysintech.db.salary.query.AddSalaryQuery
import app.paysintech.db.sql.executeTransaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.sql.Connection
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class SalaryRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SalaryRepository {
    override suspend fun addSalary(salary: AddSalary): Long = withContext(dispatcher) {
        val salaryDetail = salary.detail
        dataSource.executeTransaction { connection ->
            salaryDetail.contributionLevel?.let { checkExistContributionLevelOrThrow(connection, it) }

            val jobDetailId = addJobDetails(connection, salaryDetail) ?: error("Unable to add job details")

            salaryDetail.technologies?.let { technologies ->
                checkExistTechnologiesOrThrow(connection, technologies)
                addJobTechnologies(connection, jobDetailId, technologies)
            }
            salaryDetail.programmingLanguagesUsed?.let { programmingLanguages ->
                checkExistProgrammingLanguagesOrThrow(connection, programmingLanguages)
                addJobProgrammingLanguages(connection, jobDetailId, programmingLanguages)
            }
            salaryDetail.additionalBenefits?.benefits?.let { addJobBenefits(connection, jobDetailId, it) }
            addSalary(connection, jobDetailId, salary) ?: error("Unable to add salary details")
        }.getOrNull() ?: error("Failed to add salary details")
    }

    private fun addSalary(connection: Connection, jobDetailId: Long, salary: AddSalary): Long? =
        AddSalaryQuery().executeAsOneOrNull(connection, jobDetailId to salary)

    private fun addJobDetails(connection: Connection, salaryDetail: AddSalaryDetail): Long? =
        AddJobDetailsQuery().executeAsOneOrNull(connection, salaryDetail)

    private fun addJobTechnologies(connection: Connection, jobDetailId: Long, technologies: Set<Long>) =
        AddJobTechnologyCommand().executeBatch(connection, technologies.map { jobDetailId to it })

    private fun addJobProgrammingLanguages(
        connection: Connection,
        jobDetailId: Long,
        programmingLanguages: Set<Long>
    ) = AddJobProgrammingLanguageCommand().executeBatch(connection, programmingLanguages.map { jobDetailId to it })

    private fun addJobBenefits(
        connection: Connection,
        jobDetailId: Long,
        benefits: Set<AdditionalBenefits.Benefit>
    ) = AddJobBenefitCommand().executeBatch(connection, benefits.map { jobDetailId to it })

    private fun checkExistTechnologiesOrThrow(connection: Connection, technologyIds: Set<Long>) {
        val existedTechnologies = GetTechnologiesByIdsQuery(technologyIds)
            .executeAsList(connection, Unit).map { it.id }.toSet()

        val nonExistingTechnologyIds = technologyIds - existedTechnologies
        println("ETS: $existedTechnologies")
        if (nonExistingTechnologyIds.isNotEmpty()) {
            error("Technology IDs $nonExistingTechnologyIds not exists in the system")
        }
    }

    private fun checkExistProgrammingLanguagesOrThrow(connection: Connection, programmingLanguageIds: Set<Long>) {
        val existedProgrammingLanguages = GetProgrammingLanguagesByIdsQuery(programmingLanguageIds)
            .executeAsList(connection, Unit).map { it.id }.toSet()

        val nonExistingLanguageIds = programmingLanguageIds - existedProgrammingLanguages

        if (nonExistingLanguageIds.isNotEmpty()) {
            error("Programming language IDs $nonExistingLanguageIds not exists in the system")
        }
    }

    private fun checkExistContributionLevelOrThrow(connection: Connection, levelId: Long) {
        val existedContributionLevels = GetAllContributionLevels().executeAsList(connection, Unit).map { it.id }

        if (levelId !in existedContributionLevels) {
            error("Contribution Level ID '$levelId' not exists in the system")
        }
    }
}
