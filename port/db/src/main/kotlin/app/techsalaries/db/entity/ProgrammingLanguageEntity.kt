package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface ProgrammingLanguageEntity : Entity<ProgrammingLanguageEntity> {
    companion object : Entity.Factory<ProgrammingLanguageEntity>()

    val id: Long
    val name: String
    val enabled: Boolean
}
