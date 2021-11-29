package app.techsalaries.db.salary.query

import app.techsalaries.db.sql.Command
import java.sql.PreparedStatement

class AddJobProgrammingLanguageCommand() : Command<Pair<Long, Long>>() {

    //language=sql
    override val sql: String = """
       INSERT INTO job_programming_languages(job_detail_id, programming_language_id) VALUES (?, ?)
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: Pair<Long, Long>) = with(statement) {
        val (jobDetailId, programmingLanguageId) = param
        setLong(1, jobDetailId)
        setLong(2, programmingLanguageId)
    }
}