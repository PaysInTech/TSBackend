package app.techsalaries.db.salary

import app.techsalaries.core.IoDispatcher
import app.techsalaries.core.salary.SalaryRepository
import app.techsalaries.core.salary.model.AddSalary
import app.techsalaries.core.salary.model.AddSalaryDetail
import app.techsalaries.core.salary.model.AdditionalBenefits
import app.techsalaries.db.Config
import app.techsalaries.db.schema.JobBenefits
import app.techsalaries.db.schema.JobDetails
import app.techsalaries.db.schema.JobProgrammingLanguages
import app.techsalaries.db.schema.JobTechnologies
import app.techsalaries.db.schema.ProgrammingLanguages
import app.techsalaries.db.schema.Salaries
import app.techsalaries.db.schema.Technologies
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.inList
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.dsl.where
import org.ktorm.support.postgresql.bulkInsert
import org.ktorm.support.postgresql.insertReturning

@Singleton
class SalaryRepositoryImpl @Inject constructor(
    private val database: Database,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SalaryRepository {
    override suspend fun addSalary(salary: AddSalary): Long = withContext(dispatcher) {
        val salaryDetail = salary.detail
        database.useTransaction {
            val jobDetailId = addJobDetails(salaryDetail) ?: error("Unable to add job details")

            salaryDetail.technologies?.let { technologies ->
                checkExistTechnologiesOrThrow(technologies)
                addJobTechnologies(jobDetailId, technologies)
            }
            salaryDetail.programmingLanguagesUsed?.let { programmingLanguages ->
                checkExistProgrammingLanguagesOrThrow(programmingLanguages)
                addJobProgrammingLanguages(jobDetailId, programmingLanguages)
            }
            salaryDetail.additionalBenefits?.benefits?.let { addJobBenefits(jobDetailId, it) }
            addSalary(jobDetailId, salary) ?: error("Unable to add salary details")
        }
    }

    private fun addSalary(jobDetailId: Long, salary: AddSalary): Long? =
        database.insertReturning(Salaries, Salaries.id) {
            set(it.jobDetailId, jobDetailId)
            set(it.coins, salary.coins)
            set(it.metadata, salary.metadata)
            set(it.metadataVersion, Config.SALARY_METADATA_VERSION)
        }

    private fun addJobDetails(salaryDetail: AddSalaryDetail): Long? =
        database.insertReturning(JobDetails, JobDetails.id) {
            set(it.companyName, salaryDetail.company)
            set(it.designation, salaryDetail.designation)
            set(it.profileId, salaryDetail.profileId)
            set(it.contributionLevelId, salaryDetail.contributionLevel)
            set(it.level, salaryDetail.level?.name)
            set(it.team, salaryDetail.team)
            set(it.annualCtc, salaryDetail.salary.annualCTC)
            set(it.inhandPerMonth, salaryDetail.salary.inhandPerMonth)
            set(it.bonus, salaryDetail.salary.bonus)
            set(it.joiningDate, salaryDetail.joiningDate)
            set(it.lastWorkingDate, salaryDetail.lastWorkingDate)
            set(it.isPromoted, salaryDetail.isPromoted)
            set(it.workType, salaryDetail.workType.value)
            set(it.locationState, salaryDetail.baseLocation?.state)
            set(it.locationCity, salaryDetail.baseLocation?.city)
        }

    private fun addJobTechnologies(jobDetailId: Long, technologies: Set<Long>) = database.bulkInsert(JobTechnologies) {
        technologies.forEach { technologyId ->
            item {
                set(it.jobDetailId, jobDetailId)
                set(it.technologyId, technologyId)
            }
        }
    }

    private fun addJobProgrammingLanguages(
        jobDetailId: Long,
        programmingLanguages: Set<Long>
    ) = database.bulkInsert(JobProgrammingLanguages) {
        programmingLanguages.forEach { languageId ->
            item {
                set(it.jobDetailId, jobDetailId)
                set(it.programmingLanguageId, languageId)
            }
        }
    }

    private fun addJobBenefits(
        jobDetailId: Long,
        benefits: Set<AdditionalBenefits.Benefit>
    ) = database.bulkInsert(JobBenefits) {
        benefits.forEach { benefit ->
            item {
                set(it.jobDetailId, jobDetailId)
                set(it.type, benefit.type)
                set(it.amount, benefit.amount)
            }
        }
    }

    private fun checkExistTechnologiesOrThrow(technologyIds: Set<Long>) {
        val existedTechnologies = database
            .from(Technologies)
            .select(Technologies.id)
            .where { Technologies.id inList technologyIds }
            .map { it[Technologies.id] }
            .toSet()

        val nonExistingTechnologyIds = technologyIds - existedTechnologies

        if (nonExistingTechnologyIds.isNotEmpty()) {
            error("Technology IDs $nonExistingTechnologyIds not exists in the system")
        }
    }

    private fun checkExistProgrammingLanguagesOrThrow(programmingLanguageIds: Set<Long>) {
        val existedProgrammingLanguages = database
            .from(ProgrammingLanguages)
            .select(ProgrammingLanguages.id)
            .where { ProgrammingLanguages.id inList programmingLanguageIds }
            .map { it[ProgrammingLanguages.id] }
            .toSet()

        val nonExistingLanguageIds = programmingLanguageIds - existedProgrammingLanguages

        if (nonExistingLanguageIds.isNotEmpty()) {
            error("Programming language IDs $nonExistingLanguageIds not exists in the system")
        }
    }
}
