package app.techsalaries.db.salary.query

import app.techsalaries.core.salary.model.AdditionalBenefits.Benefit
import app.techsalaries.db.sql.Command
import java.sql.PreparedStatement

class AddJobBenefitCommand : Command<Pair<Long, Benefit>>() {

    //language=sql
    override val sql: String = """
       INSERT INTO job_benefits(job_detail_id, type, amount) VALUES (?, ?, ?)
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: Pair<Long, Benefit>) = with(statement) {
        val (jobDetailId, benefit) = param
        setLong(1, jobDetailId)
        setString(2, benefit.type)
        setDouble(3, benefit.amount)
    }
}
