package app.techsalaries.db.schema

import app.techsalaries.db.entity.JobDetailEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.date
import org.ktorm.schema.double
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object JobDetails : Table<JobDetailEntity>("job_details") {
    val id = long("id").primaryKey().bindTo { it.id }
    val companyName = varchar("company_name").bindTo { it.companyName }
    val designation = varchar("designation").bindTo { it.designation }
    val profileId = long("profile_id").references(JobProfiles) { it.profile }
    val contributionLevelId = long("contribution_level_id").references(ContributionLevels) { it.contributionLevel }
    val level = varchar("level").bindTo { it.level }
    val team = varchar("team").bindTo { it.team }
    val annualCtc = double("annual_ctc").bindTo { it.annualCtc }
    val inhandPerMonth = double("in_hand_per_month").bindTo { it.inhandPerMonth }
    val bonus = double("bonus").bindTo { it.bonus }
    val joiningDate = date("joining_date").bindTo { it.joiningDate }
    val lastWorkingDate = date("last_working_date").bindTo { it.lastWorkingDate }
    val isPromoted = boolean("is_promoted").bindTo { it.isPromoted }
    val workType = varchar("work_type").bindTo { it.workType }
    val locationState = varchar("location_state").bindTo { it.locationState }
    val locationCity = varchar("location_city").bindTo { it.locationCity }
}
