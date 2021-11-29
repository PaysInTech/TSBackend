package app.techsalaries.db.utils.sql

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

fun Connection.executeCommand(sql: String, params: List<Any?> = listOf()): Int =
    this.prepareStatement(sql)
        .withParams(params)
        .use { it.executeUpdate() }

fun Connection.executeCommand(sql: String, statement: (PreparedStatement) -> Unit): Int =
    this.prepareStatement(sql)
        .apply(statement)
        .use { it.executeUpdate() }

fun Connection.batchExecuteCommand(sql: String, batchedParams: List<List<Any?>> = listOf()): List<Int> =
    this.prepareStatement(sql)
        .withBatches(batchedParams)
        .use { it.executeBatch() }
        .toList()

fun Connection.executeQuery(sql: String, params: List<Any?> = listOf()): ResultSet =
    this.prepareStatement(sql)
        .withParams(params)
        .executeQuery()

fun Connection.executeQuery(sql: String, statement: (PreparedStatement) -> Unit): ResultSet =
    this.prepareStatement(sql)
        .apply(statement)
        .executeQuery()

fun Connection.query(sql: String, vararg params: Any) =
    this.executeQuery(sql, params.toList()).toList()
