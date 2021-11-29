package app.techsalaries.db.sql

import app.techsalaries.db.utils.sql.executeCommand
import java.sql.Connection
import java.sql.PreparedStatement
import javax.sql.DataSource

abstract class Command<P> {

    /**
     * Provide SQL statement
     */
    abstract val sql: String

    /**
     * To provide list of parameters for SQL Prepared Statement.
     */
    open fun setParameters(statement: PreparedStatement, param: P) {}

    /**
     * Executes command using [dataSource] and returns count of updated rows
     */
    fun execute(dataSource: DataSource, param: P): Int {
        return dataSource.connection.use { execute(it, param) }
    }

    /**
     * Executes command using [connection] and returns count of updated rows.
     * Make sure you close [connection] once operation is done.
     */
    fun execute(connection: Connection, param: P): Int {
        return connection.executeCommand(sql = sql) { statement -> setParameters(statement, param) }
    }

    /**
     * Executes command in batch using [dataSource] and returns count of updated rows
     */
    fun executeBatch(dataSource: DataSource, params: List<P>): IntArray {
        return executeBatch(dataSource.connection, params)
    }

    /**
     * Executes command in batch using [connection] and returns count of updated rows
     * Make sure you close [connection] once operation is done.
     */
    fun executeBatch(connection: Connection, params: List<P>): IntArray {
        connection.autoCommit = false

        return connection.prepareStatement(sql).also { statement ->
            params.forEach { param ->
                setParameters(statement, param)
                statement.addBatch()
            }
        }.run {
            executeBatch()
        }.also {
            connection.commit()
        }
    }
}