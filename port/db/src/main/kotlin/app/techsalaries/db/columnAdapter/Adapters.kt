package app.techsalaries.db.columnAdapter

import app.techsalaries.core.salary.model.Metadata
import app.techsalaries.core.utils.fromJson
import app.techsalaries.core.utils.json
import java.sql.Date
import java.time.LocalDate
import org.postgresql.util.PGobject

object DateAdapter : Adapter<LocalDate, Date> {
    override fun decode(value: Date): LocalDate = value.toLocalDate()
    override fun encode(value: LocalDate): Date = Date.valueOf(value)
}

object MetadataAdapter : Adapter<Metadata, PGobject> {
    override fun decode(value: PGobject): Metadata = value.value?.let { fromJson(it) }!!

    override fun encode(value: Metadata): PGobject = PGobject().apply {
        this.type = "jsonb"
        this.value = value.json()
    }
}

object StringArrayAdapter: Adapter<List<String>, String> {
    override fun decode(value: String): List<String> = value.split(",")
    override fun encode(value: List<String>): String = value.joinToString(",")

}