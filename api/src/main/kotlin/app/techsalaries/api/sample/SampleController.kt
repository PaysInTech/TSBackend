package app.techsalaries.api.sample

import app.techsalaries.api.sample.model.SampleResponse
import app.techsalaries.core.sample.SampleService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleController @Inject constructor(private val service: SampleService) {
    fun getAllSamples(): List<SampleResponse> {
        return service.getAllSampleTexts().map { SampleResponse(it.id, it.someText) }
    }
}
