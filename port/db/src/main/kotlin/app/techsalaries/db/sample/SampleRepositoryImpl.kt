package app.techsalaries.db.sample

import app.techsalaries.core.sample.SampleRepository
import app.techsalaries.core.sample.model.Sample
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepositoryImpl @Inject constructor() : SampleRepository {
    override fun getAllSampleTexts(): List<Sample> {
        return Samples.selectAll().mapNotNull { toSample(it) }
    }

    private fun toSample(row: ResultRow): Sample {
        return Sample(
            id = row[Samples.id].value.toLong(),
            someText = row[Samples.someText]
        )
    }
}
