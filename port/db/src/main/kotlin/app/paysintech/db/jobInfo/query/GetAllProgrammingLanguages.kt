package app.paysintech.db.jobInfo.query

import app.paysintech.core.jobInfo.model.ProgrammingLanguage
import app.paysintech.db.sql.Query

class GetAllProgrammingLanguages() : Query<ProgrammingLanguage, Unit>() {
    //language=sql
    override val sql: String = """
        SELECT * FROM programming_languages
        WHERE enabled = true
    """.trimIndent()

    override fun mapResult(result: Map<String, Any?>): ProgrammingLanguage = ProgrammingLanguage(
        id = result["id"] as Long,
        name = result["name"] as String
    )
}
