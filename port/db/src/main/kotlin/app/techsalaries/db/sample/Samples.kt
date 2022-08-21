package app.techsalaries.db.sample

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Samples : IntIdTable("samples") {
    val someText: Column<String> = varchar("some_text", 255)
}
