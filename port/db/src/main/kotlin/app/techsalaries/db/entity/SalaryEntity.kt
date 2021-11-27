package app.techsalaries.db.entity

import app.techsalaries.core.salary.model.Metadata
import org.ktorm.entity.Entity

interface SalaryEntity : Entity<SalaryEntity> {
    val id: Long
    val jobDetail: JobDetailEntity
    val coins: Int
    val metadata: Metadata
    val metadataVersion: Int
}
