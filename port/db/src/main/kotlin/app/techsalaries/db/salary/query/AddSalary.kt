package app.techsalaries.db.salary.query

import app.techsalaries.core.salary.model.AddSalary
import app.techsalaries.db.Config
import app.techsalaries.db.columnAdapter.MetadataAdapter
import app.techsalaries.db.sql.Query
import java.sql.PreparedStatement

class AddSalaryQuery : Query<Long, Pair<Long, AddSalary>>() {

    //language=sql
    override val sql: String = """
       INSERT INTO salaries(job_detail_id, coins, currency, user_id, metadata, metadata_version)
       VALUES (?, ?, ?, ?, ?, ?)
       RETURNING id;
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: Pair<Long, AddSalary>) = with(statement) {
        val (jobDetailId, salary) = param
        setLong(1, jobDetailId)
        setInt(2, salary.coins)
        setString(3, salary.currency.currencyCode)
        setString(4, salary.userId)
        setObject(5, MetadataAdapter.encode(salary.metadata))
        setInt(6, Config.SALARY_METADATA_VERSION)
    }

    override fun mapResult(result: Map<String, Any?>): Long = result["id"] as Long
}