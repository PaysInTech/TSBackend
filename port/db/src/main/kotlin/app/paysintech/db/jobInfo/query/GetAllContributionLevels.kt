package app.paysintech.db.jobInfo.query

import app.paysintech.core.jobInfo.model.ContributionLevel
import app.paysintech.db.sql.Query

class GetAllContributionLevels() : Query<ContributionLevel, Unit>() {
    //language=sql
    override val sql: String = """
        SELECT * FROM contribution_levels
        WHERE enabled = true
    """.trimIndent()

    override fun mapResult(result: Map<String, Any?>): ContributionLevel = ContributionLevel(
        id = result["id"] as Long,
        name = result["name"] as String
    )
}
