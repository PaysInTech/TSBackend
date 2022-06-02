package app.paysintech.api.salary

import app.paysintech.api.response.HttpResponse
import app.paysintech.api.response.Success
import app.paysintech.api.salary.model.AddSalaryDetailRequest
import app.paysintech.api.salary.model.AddSalaryDetailResponse
import app.paysintech.api.salary.model.toAddSalaryDetail
import app.paysintech.core.salary.SalaryService
import app.paysintech.core.salary.model.Metadata
import app.paysintech.core.user.model.User
import app.paysintech.core.utils.getCurrency
import app.paysintech.exception.CommonErrors
import app.paysintech.exception.badRequest
import app.paysintech.utils.handleResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalaryController @Inject constructor(private val service: SalaryService) {
    suspend fun submitSalary(
        addSalaryDetailRequest: AddSalaryDetailRequest,
        currencyCode: String,
        user: User?,
        ipAddress: String,
        location: String? = null
    ): HttpResponse<AddSalaryDetailResponse> = handleResponse {
        val currency = getCurrency(currencyCode) ?: badRequest(CommonErrors.INVALID_CURRENCY_CODE)
        val detail = addSalaryDetailRequest.toAddSalaryDetail()
        val (salaryId, coins) = service.addSalary(detail, currency, Metadata(ipAddress, location), user)
        Success(AddSalaryDetailResponse(salaryId, coins))
    }
}
