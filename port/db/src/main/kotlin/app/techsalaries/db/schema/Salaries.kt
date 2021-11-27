package app.techsalaries.db.schema

import app.techsalaries.core.salary.model.Metadata
import app.techsalaries.core.utils.fromJson
import app.techsalaries.core.utils.json
import app.techsalaries.db.entity.SalaryEntity
import org.ktorm.schema.BaseTable
import org.ktorm.schema.SqlType
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.postgresql.util.PGobject
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

object Salaries : Table<SalaryEntity>("salaries") {
    val id = long("id").primaryKey().bindTo { it.id }
    val jobDetailId = long("job_detail_id").references(JobDetails) { it.jobDetail }
    val coins = int("coins").bindTo { it.coins }
    val metadata = metadata("metadata").bindTo { it.metadata }
    val metadataVersion = int("metadata_version").bindTo { it.metadataVersion }
}

object MetadataSqlType : SqlType<Metadata>(Types.OTHER, "jsonb") {

    override fun doGetResult(rs: ResultSet, index: Int): Metadata? {
        return (rs.getObject(index) as PGobject).value?.let { fromJson(it) }
    }

    override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: Metadata) {
        ps.setObject(
            index,
            PGobject().apply {
                type = "jsonb"
                value = parameter.json()
            }
        )
    }
}

fun BaseTable<*>.metadata(name: String) = registerColumn(name, MetadataSqlType)
