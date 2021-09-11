package app.techsalaries.db.sample

import app.techsalaries.core.sample.SampleRepository
import app.techsalaries.core.sample.model.Sample
import javax.inject.Inject
import javax.inject.Singleton
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select

@Singleton
class SampleRepositoryImpl @Inject constructor(private val database: Database) : SampleRepository {
    override fun getAllSampleTexts(): List<Sample> {
        return database
            .from(Samples)
            .select()
            .map { Sample(it[Samples.id]!!, it[Samples.someText]) }
    }
}