package app.techsalaries.db.jobInfo.query

import app.techsalaries.core.jobInfo.model.ContributionLevel
import app.techsalaries.db.sql.Query

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
