package app.techsalaries.core.sample

import app.techsalaries.core.sample.model.Sample
import javax.inject.Singleton

@Singleton
interface SampleRepository {
    fun getAllSampleTexts(): List<Sample>
}
