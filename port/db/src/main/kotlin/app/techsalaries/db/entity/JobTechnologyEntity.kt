package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface JobTechnologyEntity : Entity<JobTechnologyEntity> {
    val id: Long
    val jobDetail: JobDetailEntity
    val technology: TechnologyEntity
}
