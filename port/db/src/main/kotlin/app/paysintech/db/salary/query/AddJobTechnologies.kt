package app.paysintech.db.salary.query

import app.paysintech.db.sql.Command
import java.sql.PreparedStatement

class AddJobTechnologyCommand() : Command<Pair<Long, Long>>() {

    //language=sql
    override val sql: String = """
       INSERT INTO job_technologies(job_detail_id, technology_id) VALUES (?, ?)
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: Pair<Long, Long>) = with(statement) {
        val (jobDetailId, technologyId) = param
        setLong(1, jobDetailId)
        setLong(2, technologyId)
    }
}
