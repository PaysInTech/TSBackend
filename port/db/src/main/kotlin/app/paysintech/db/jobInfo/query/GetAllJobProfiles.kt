package app.paysintech.db.jobInfo.query

import app.paysintech.core.jobInfo.model.JobProfile
import app.paysintech.db.sql.Query

class GetAllJobProfilesQuery() : Query<JobProfile, Unit>() {
    //language=sql
    override val sql: String = """
        SELECT * FROM job_profiles
        WHERE enabled = true
    """.trimIndent()

    override fun mapResult(result: Map<String, Any?>): JobProfile = JobProfile(
        id = result["id"] as Long,
        name = result["name"] as String
    )
}
