package app.techsalaries.db.entity

import org.ktorm.entity.Entity
import java.time.LocalDate

interface JobDetailEntity : Entity<JobDetailEntity> {
    val id: Long
    val companyName: String
    val designation: String
    val profile: JobProfileEntity
    val contributionLevel: ContributionLevelEntity
    val level: String
    val team: String
    val annualCtc: Double
    val inhandPerMonth: Double
    val bonus: Double?
    val joiningDate: LocalDate
    val lastWorkingDate: LocalDate?
    val isPromoted: Boolean
    val workType: String
    val locationState: String
    val locationCity: String
}
