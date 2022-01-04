package app.paysintech.db.utils.sql

import app.paysintech.db.sql.Command
import java.sql.PreparedStatement
import javax.sql.DataSource

fun PreparedStatement.withParams(params: List<Any?> = listOf()): PreparedStatement =
    this.also { self ->
        params.forEachIndexed { index, param ->
            self.setObject(index + 1, param)
        }
    }

fun PreparedStatement.withBatches(batchedParams: List<List<Any?>> = listOf()) =
    this.also { ps ->
        batchedParams.forEach { params ->
            ps.withParams(params).addBatch()
        }
    }

fun <P : Any> DataSource.batchCommand(command: Command<P>, params: List<P>): IntArray =
    connection.use { connection ->
        connection.autoCommit = false

        connection.prepareStatement(command.sql)
            .also {
                params.forEach { param ->
                    command.setParameters(it, param)
                    it.addBatch()
                }
            }
            .run { executeBatch() }
            .also { connection.commit() }
    }
