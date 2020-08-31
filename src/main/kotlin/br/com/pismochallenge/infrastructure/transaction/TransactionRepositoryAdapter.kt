package br.com.pismochallenge.infrastructure.transaction

import br.com.pismochallenge.core.transaction.Transaction
import br.com.pismochallenge.core.transaction.ports.TransactionRepository
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

internal object TransactionTable : Table("transactions") {

    private const val VARCHAR_MAX_LENGTH = 50

    val id = TransactionTable.uuid("id")
    val accountId = TransactionTable.varchar("account_id", VARCHAR_MAX_LENGTH)
    val amount = TransactionTable.decimal("amount", 2, 0)
    val createdAt = TransactionTable.datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}

class TransactionRepositoryAdapter :
    TransactionRepository {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun save(transaction: Transaction) {
        logger.info("Persisting transaction: ${transaction.id} into database")

        transaction {
            TransactionTable.insert {
                it[id] = transaction.id
                it[accountId] = transaction.accountId
                it[amount] = transaction.amount
                it[createdAt] = transaction.createdAt
            }
        }
    }
}
