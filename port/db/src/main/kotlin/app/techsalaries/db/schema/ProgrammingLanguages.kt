package app.techsalaries.db.schema

import app.techsalaries.db.entity.ProgrammingLanguageEntity
import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object ProgrammingLanguages : Table<ProgrammingLanguageEntity>("programming_languages") {
    val id = long("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val enabled = boolean("enabled").bindTo { it.enabled }
}
