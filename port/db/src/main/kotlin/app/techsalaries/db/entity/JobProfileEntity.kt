package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface JobProfileEntity : Entity<JobProfileEntity> {
    companion object : Entity.Factory<JobProfileEntity>()

    val id: Long
    val name: String
    val enabled: Boolean
}
