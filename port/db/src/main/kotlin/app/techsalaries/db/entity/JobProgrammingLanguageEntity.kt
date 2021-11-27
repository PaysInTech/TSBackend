package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface JobProgrammingLanguageEntity : Entity<JobProgrammingLanguageEntity> {
    val id: Long
    val jobDetail: JobDetailEntity
    val programmingLanguage: ProgrammingLanguageEntity
}
