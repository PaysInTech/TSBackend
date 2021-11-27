package app.techsalaries.db.schema

import app.techsalaries.db.entity.JobBenefitEntity
import org.ktorm.schema.Table
import org.ktorm.schema.double
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object JobBenefits : Table<JobBenefitEntity>("job_benefits") {
    val id = long("id").primaryKey()
    val jobDetailId = long("job_detail_id").references(JobDetails) { it.jobDetail }
    val type = varchar("type").bindTo { it.type }
    val amount = double("amount").bindTo { it.amount }
}
