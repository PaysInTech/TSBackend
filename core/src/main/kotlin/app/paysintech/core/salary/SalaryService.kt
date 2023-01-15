package app.paysintech.core.salary

import app.paysintech.core.salary.model.AddSalary
import app.paysintech.core.salary.model.AddSalaryDetail
import app.paysintech.core.salary.model.Metadata
import app.paysintech.core.salary.util.CoinsCounter
import app.paysintech.core.user.model.User
import java.util.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalaryService @Inject constructor(private val salaryRepository: SalaryRepository) {
    suspend fun addSalary(
        detail: AddSalaryDetail,
        currency: Currency,
        metadata: Metadata,
        user: User? = null
    ): Pair<Long, Int> {
        val coins = CoinsCounter(detail, authentication = user != null).count
        val salary = AddSalary(detail, coins, currency, metadata, user?.id)
        val salaryId = salaryRepository.addSalary(salary)
        return salaryId to coins
    }
}
