package app.techsalaries.db.salary.query

import app.techsalaries.core.salary.model.AddSalaryDetail
import app.techsalaries.db.columnAdapter.DateAdapter
import app.techsalaries.db.sql.Query
import java.sql.PreparedStatement

class AddJobDetailsQuery() : Query<Long, AddSalaryDetail>() {

    //language=sql
    override val sql: String = """
       INSERT INTO job_details(company_name,
                               designation,
                               profile_id,
                               contribution_level_id,
                               level,
                               team,
                               annual_ctc,
                               in_hand_per_month,
                               bonus,
                               joining_date,
                               last_working_date,
                               is_promoted,
                               work_type,
                               location_state,
                               location_city)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
       RETURNING id;
    """.trimIndent()

    override fun setParameters(statement: PreparedStatement, param: AddSalaryDetail) = with(statement) {
        setString(1, param.company)
        setString(2, param.designation)
        setLong(3, param.profileId)
        setObject(4, param.contributionLevel)
        setString(5, param.level?.name)
        setString(6, param.team)
        setDouble(7, param.salary.annualCTC)
        setDouble(8, param.salary.inhandPerMonth)
        setObject(9, param.salary.bonus)
        setDate(10, DateAdapter.encode(param.joiningDate))
        setDate(11, param.lastWorkingDate?.let { DateAdapter.encode(it) })
        setObject(12, param.isPromoted)
        setString(13, param.workType.value)
        setString(14, param.baseLocation?.state)
        setString(15, param.baseLocation?.city)
    }

    override fun mapResult(result: Map<String, Any?>): Long = result["id"] as Long

}