package app.techsalaries.core.salary

import app.techsalaries.core.salary.model.AddSalary
import app.techsalaries.core.salary.model.AddSalaryDetail
import app.techsalaries.core.salary.model.Metadata
import app.techsalaries.core.salary.util.CoinsCounter
import app.techsalaries.core.user.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalaryService @Inject constructor(private val salaryRepository: SalaryRepository) {
    suspend fun addSalary(detail: AddSalaryDetail, metadata: Metadata, user: User? = null): Pair<Long, Int> {
        val coins = CoinsCounter(detail, authentication = user != null).count
        val salary = AddSalary(detail, coins, metadata)
        val salaryId = salaryRepository.addSalary(salary)
        return salaryId to coins
    }
}
