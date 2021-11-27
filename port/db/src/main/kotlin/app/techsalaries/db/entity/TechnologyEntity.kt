package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface TechnologyEntity : Entity<TechnologyEntity> {
    companion object : Entity.Factory<TechnologyEntity>()

    val id: Long
    val name: String
    val enabled: Boolean
}
