package app.paysintech.db.jobInfo.query

import app.paysintech.core.jobInfo.model.Technology
import app.paysintech.db.sql.Query

class GetAllTechnologies() : Query<Technology, Unit>() {
    //language=sql
    override val sql: String = """
        SELECT * FROM technologies
        WHERE enabled = true
    """.trimIndent()

    override fun mapResult(result: Map<String, Any?>): Technology = Technology(
        id = result["id"] as Long,
        name = result["name"] as String
    )
}
