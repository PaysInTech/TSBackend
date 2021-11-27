package app.techsalaries.db.schema

import app.techsalaries.db.entity.JobTechnologyEntity
import org.ktorm.schema.Table
import org.ktorm.schema.long

object JobTechnologies : Table<JobTechnologyEntity>("job_technologies") {
    val id = long("id").primaryKey()
    val jobDetailId = long("job_detail_id").references(JobDetails) { it.jobDetail }
    val technologyId = long("technology_id").references(Technologies) { it.technology }
}
