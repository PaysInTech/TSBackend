package app.techsalaries.db.schema

import app.techsalaries.db.entity.JobProgrammingLanguageEntity
import app.techsalaries.db.schema.JobTechnologies.primaryKey
import app.techsalaries.db.schema.JobTechnologies.references
import org.ktorm.schema.Table
import org.ktorm.schema.long

object JobProgrammingLanguages : Table<JobProgrammingLanguageEntity>("job_programming_languages") {
    val id = long("id").primaryKey()
    val jobDetailId = long("job_detail_id").references(JobDetails) { it.jobDetail }
    val programmingLanguageId = long("programming_language_id").references(ProgrammingLanguages) { it.programmingLanguage }
}
