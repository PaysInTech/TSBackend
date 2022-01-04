package app.paysintech.api.salary.model

import app.paysintech.core.salary.model.AddSalaryDetail
import app.paysintech.core.salary.model.AdditionalBenefits
import app.paysintech.core.salary.model.Level
import app.paysintech.core.salary.model.Location
import app.paysintech.core.salary.model.SalaryDetails
import app.paysintech.core.salary.model.WorkType
import app.paysintech.core.utils.localDateFrom
import app.paysintech.exception.CommonErrors
import app.paysintech.exception.badRequest
import app.paysintech.utils.validate
import kotlinx.serialization.Serializable

@Serializable
data class AddSalaryDetailRequest(
    val company: String,
    val designation: String,
    val profileId: Long,
    val contributionLevel: Long?,
    val additionalBenefits: AdditionalBenefitDetails?,
    val salary: SalaryDetails,
    val level: String?,
    val team: String?,
    val joiningDate: String,
    val lastWorkingDate: String,
    val isPromoted: Boolean?,
    val workType: String,
    val baseLocation: BaseLocation?,
    val availableInIndia: Boolean,
    val technologies: List<Long>?,
    val programmingLanguagesUsed: List<Long>?
) {

    init {
        // Validate additional benefit total
        additionalBenefits?.total?.let { total ->
            validate(total <= salary.annualCTC || total <= salary.inhandPerMonth) { CommonErrors.INVALID_BENEFIT_ERROR }
        }
    }

    @Serializable
    data class AdditionalBenefitDetails(val benefits: List<Benefit>) {

        val total: Double get() = benefits.sumOf { it.amount }

        @Serializable
        data class Benefit(val type: String, val amount: Double)
    }

    @Serializable
    data class SalaryDetails(val annualCTC: Double, val inhandPerMonth: Double, val bonus: Double?) {
        init {
            validate(inhandPerMonth <= (annualCTC / 12.0)) { CommonErrors.INVALID_IN_HAND_SALARY_ERROR }
        }
    }

    @Serializable
    data class BaseLocation(val state: String, val city: String)
}

fun AddSalaryDetailRequest.toAddSalaryDetail() = AddSalaryDetail(
    company = company,
    designation = designation,
    profileId = profileId,
    contributionLevel = contributionLevel,
    additionalBenefits = additionalBenefits?.let {
        AdditionalBenefits(
            it.benefits.map { benefit -> AdditionalBenefits.Benefit(benefit.type, benefit.amount) }.toSet()
        )
    },
    salary = salary.let { SalaryDetails(it.annualCTC, it.inhandPerMonth, it.bonus) },
    level = level?.let { runCatching { Level.valueOf(it) }.getOrElse { badRequest(CommonErrors.INVALID_JOB_LEVEL) } },
    team = team,
    joiningDate = localDateFrom(joiningDate) ?: badRequest(CommonErrors.DATE_PARSE_ERROR),
    lastWorkingDate = lastWorkingDate.takeIf { it.isNotBlank() }
        ?.let { localDateFrom(it) ?: badRequest(CommonErrors.DATE_PARSE_ERROR) },
    isPromoted = isPromoted,
    workType = WorkType.by(workType) ?: badRequest(CommonErrors.INVALID_WORK_TYPE_ERROR),
    baseLocation = baseLocation?.let { Location(it.state, it.city) },
    availableInIndia = availableInIndia,
    technologies = technologies?.toSet(),
    programmingLanguagesUsed = programmingLanguagesUsed?.toSet()
)
