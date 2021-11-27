package app.techsalaries.api.salary

import app.techsalaries.api.health.model.HealthResponse
import app.techsalaries.api.response.HttpResponse
import app.techsalaries.api.response.Success
import app.techsalaries.api.salary.model.AddSalaryDetailRequest
import app.techsalaries.api.salary.model.AddSalaryDetailResponse
import app.techsalaries.api.salary.model.toAddSalaryDetail
import app.techsalaries.core.salary.SalaryService
import app.techsalaries.core.salary.model.Metadata
import app.techsalaries.utils.handleResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalaryController @Inject constructor(private val service: SalaryService) {
    suspend fun submitSalary(
        addSalaryDetailRequest: AddSalaryDetailRequest,
        ipAddress: String,
        location: String? = null
    ): HttpResponse<AddSalaryDetailResponse> = handleResponse {
        val detail = addSalaryDetailRequest.toAddSalaryDetail()
        val (salaryId, coins) = service.addSalary(detail, Metadata(ipAddress, location))
        Success(AddSalaryDetailResponse(salaryId, coins))
    }
}
