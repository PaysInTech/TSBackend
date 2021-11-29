package app.techsalaries.db.utils.sql

import javax.sql.DataSource

fun DataSource.query(sql: String, params: List<Any?>) =
    this.connection.use { it.executeQuery(sql, params).toList() }

fun DataSource.command(sql: String, params: List<Any?>) =
    this.connection.use { it.executeCommand(sql, params) }
