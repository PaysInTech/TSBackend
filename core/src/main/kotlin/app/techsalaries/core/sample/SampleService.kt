package app.techsalaries.core.sample

import javax.inject.Inject

class SampleService @Inject constructor(private val sampleRepository: SampleRepository) {
    fun getAllSampleTexts() = sampleRepository.getAllSampleTexts()
}
