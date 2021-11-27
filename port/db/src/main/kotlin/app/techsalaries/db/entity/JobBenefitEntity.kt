package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface JobBenefitEntity : Entity<JobBenefitEntity> {
    val id: Long
    val jobDetail: JobDetailEntity
    val type: String
    val amount: Double
}
