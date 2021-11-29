package app.techsalaries.db.utils.sql

import java.sql.ResultSet

@Suppress("UNCHECKED_CAST")
operator fun <T : Any?> ResultSet.get(columnId: Int): T? = this.getObject(columnId) as? T

@Suppress("UNCHECKED_CAST")
operator fun <T : Any?> ResultSet.get(columnName: String): T? =
    this.getObject(columnName) as? T

fun ResultSet.getColumnNames(): List<String> =
    (1..metaData.columnCount).map { metaData.getColumnName(it) }

fun ResultSet.rowAsList(columnNames: List<String> = getColumnNames()): List<Any?> =
    columnNames.map { this[it] }

fun ResultSet.rowAsMap(columnNames: List<String> = getColumnNames()): Map<String, Any?> =
    columnNames.associateWith { get(it) }

fun ResultSet.toList(columnNames: List<String> = getColumnNames()): List<Map<String, Any?>> =
    this.use { generateSequence { if (this.next()) this.rowAsMap(columnNames) else null }.toList() }

fun ResultSet.toTable(columnNames: List<String> = getColumnNames()): List<List<Any?>> =
    this.use { generateSequence { if (this.next()) this.rowAsList(columnNames) else null }.toList() }
