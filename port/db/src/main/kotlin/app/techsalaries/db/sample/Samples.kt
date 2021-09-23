package app.techsalaries.db.sample

import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

object Samples : Table<Nothing>("samples") {
    val id = long("id").primaryKey()
    val someText = varchar("some_text")
}
