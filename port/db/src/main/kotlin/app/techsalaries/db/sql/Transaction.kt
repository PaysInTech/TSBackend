package app.techsalaries.db.sql

import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource

/**
 * A result model which will be returned on execution on [executeTransaction].
 */
sealed class TransactionResult<R> {
    data class Success<R>(val data: R) : TransactionResult<R>()
    data class Error<R>(val error: Throwable) : TransactionResult<R>()

    /**
     * Returns `true` if transaction is successful. Else `false`.
     */
    val isSuccessful: Boolean get() = this is Success

    /**
     * Forcefully tries to provide a successful result data.
     * If transaction is not successful, it throws [IllegalStateException].
     */
    fun get(): R = runCatching { (this as Success).data }
        .getOrElse { throw IllegalStateException("Transaction is not successful") }

    /**
     * @return a result if transaction is successful otherwise null.
     */
    fun getOrNull(): R? = if (this is Success) data else null

    /**
     * @return an error if transaction is failed otherwise null.
     */
    fun errorOrNull(): Throwable? = if (this is Error) error else null
}

/**
 * Executes queries specified by [block] in the transaction.
 *
 * @param block Lambda block with connection on which transaction queries will be executed.
 * @return The transaction result.
 *
 * Example:
 *
 * ```
 *     val result = dataSource.executeTransaction {
 *         val user = FindUserByIdQuery(id).execute(it)
 *         AddUserItemOneCommand(item1, user.id).execute(it)
 *         AddUserItemOneCommand(item2, user.id).execute(it)
 *     }
 *
 *     // Check whether transaction is successful
 *     val isSuccessful = result.isSuccessful
 *
 *     // Retrieve transaction result
 *     val viewCount = result.get() // or `result.getOrNull()` to retrieve safely.
 *
 *     // Retrieve exception (if it's failed)
 *     val error = result.errorOrNull()
 * ```
 */
fun <R> DataSource.executeTransaction(block: (Connection) -> R): TransactionResult<R> {
    return connection.use { txConnection ->
        txConnection.autoCommit = false
        try {
            block(txConnection)
                .also { txConnection.commit() }
                .let { result -> TransactionResult.Success(result) }
        } catch (exception: SQLException) {
            exception.printStackTrace()

            val rollbackError = runCatching { txConnection.rollback() }.exceptionOrNull()
            TransactionResult.Error(rollbackError ?: exception)
        }
    }
}