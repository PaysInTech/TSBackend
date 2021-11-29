package app.techsalaries.db.sql

import app.techsalaries.db.utils.sql.executeQuery
import app.techsalaries.db.utils.sql.toList
import java.sql.Connection
import java.sql.PreparedStatement
import javax.sql.DataSource

abstract class Query<R : Any, P : Any> {

    /**
     * Provide SQL statement
     */
    abstract val sql: String

    /**
     * To provide list of parameters for SQL Prepared Statement
     */
    open fun setParameters(statement: PreparedStatement, param: P) {}

    /**
     * Maps result set to your model
     */
    abstract fun mapResult(result: Map<String, Any?>): R

    /**
     * Executes query and returns list of result
     */
    fun executeAsList(dataSource: DataSource, param: P): List<R> = execute(dataSource, param)

    /**
     * Executes query and returns list of result.
     * Make sure to close the [Connection] once operation is done.
     */
    fun executeAsList(connection: Connection, param: P): List<R> = execute(connection, param)

    /**
     * Executes query and returns list of result or null on failure
     */
    fun executeAsListOrNull(dataSource: DataSource, param: P): List<R>? =
        runCatching { execute(dataSource, param) }.onFailure { it.printStackTrace() }.getOrNull()

    /**
     * Executes query and returns list of result or null on failure.
     * Make sure to close the [Connection] once operation is done.
     */
    fun executeAsListOrNull(connection: Connection, param: P): List<R>? =
        runCatching { execute(connection, param) }.onFailure { it.printStackTrace() }.getOrNull()

    /**
     * Executes query and returns first result
     */
    fun executeAsOne(dataSource: DataSource, param: P): R =
        executeAsList(dataSource, param).first()

    /**
     * Executes query and returns first result
     * Make sure to close the [Connection] once operation is done.
     */
    fun executeAsOne(connection: Connection, param: P): R =
        executeAsList(connection, param).first()

    /**
     * Executes query and returns first result or null on failure
     */
    fun executeAsOneOrNull(dataSource: DataSource, param: P): R? = executeAsListOrNull(dataSource, param)?.first()

    /**
     * Executes query and returns first result or null on failure
     * Make sure to close the [Connection] once operation is done.
     */
    fun executeAsOneOrNull(connection: Connection, param: P): R? = executeAsListOrNull(connection, param)?.first()

    private fun execute(dataSource: DataSource, param: P): List<R> {
        return dataSource.connection.use { execute(it, param) }
    }

    private fun execute(connection: Connection, param: P): List<R> {
        return connection.executeQuery(sql) { statement -> setParameters(statement, param) }
            .toList()
            .map { mapResult(it) }
    }
}
