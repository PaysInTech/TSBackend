package app.techsalaries.db.jobInfo

import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object ProgrammingLanguages : Table<Nothing>("programming_languages") {
    val id = long("id").primaryKey()
    val name = varchar("name")
    val enabled = boolean("enabled")
}

object Technologies : Table<Nothing>("technologies") {
    val id = long("id").primaryKey()
    val name = varchar("name")
    val enabled = boolean("enabled")
}

object JobProfiles : Table<Nothing>("job_profiles") {
    val id = long("id").primaryKey()
    val name = varchar("name")
    val enabled = boolean("enabled")
}
