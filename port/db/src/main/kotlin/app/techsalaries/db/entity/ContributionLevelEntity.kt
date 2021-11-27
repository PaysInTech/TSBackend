package app.techsalaries.db.entity

import org.ktorm.entity.Entity

interface ContributionLevelEntity : Entity<ContributionLevelEntity> {
    companion object : Entity.Factory<ContributionLevelEntity>()

    val id: Long
    val name: String
    val enabled: Boolean
}
