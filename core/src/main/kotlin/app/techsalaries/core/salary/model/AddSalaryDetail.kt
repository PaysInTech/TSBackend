package app.techsalaries.core.salary.model

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.Currency

data class AddSalary(
    val detail: AddSalaryDetail,
    val coins: Int,
    val currency: Currency,
    val metadata: Metadata,
    val userId: String?
)

data class AddSalaryDetail(
    val company: String,
    val designation: String,
    val profileId: Long,
    val contributionLevel: Long?,
    val additionalBenefits: AdditionalBenefits?,
    val salary: SalaryDetails,
    val level: Level?,
    val team: String?,
    val joiningDate: LocalDate,
    val lastWorkingDate: LocalDate?,
    val isPromoted: Boolean?,
    val workType: WorkType,
    val baseLocation: Location?,
    val availableInIndia: Boolean,
    val technologies: Set<Long>?,
    val programmingLanguagesUsed: Set<Long>?
)

data class AdditionalBenefits(val benefits: Set<Benefit>) {
    data class Benefit(val type: String, val amount: Double)
}

data class SalaryDetails(val annualCTC: Double, val inhandPerMonth: Double, val bonus: Double?)

data class Location(val state: String?, val city: String?)

@Serializable
data class Metadata(val ip: String, val location: String?)
