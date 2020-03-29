package br.com.pismochallenge.infrastructure.account

import br.com.pismochallenge.core.account.Account
import br.com.pismochallenge.core.account.ports.AccountRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import java.util.UUID

internal object AccountTable : Table("accounts") {

    private const val VARCHAR_MAX_LENGTH = 50

    val id = uuid("id")
    val documentNumber = varchar("document_number", VARCHAR_MAX_LENGTH)
    val availableCreditLimit = decimal("available_credit_limit", 2, 0)

    override val primaryKey = PrimaryKey(id)

    fun toAccount(resultRow: ResultRow) = Account(
        id = resultRow[id],
        documentNumber = resultRow[documentNumber],
        availableCreditLimit = resultRow[availableCreditLimit]
    )
}

class AccountRepositoryAdapter : AccountRepository {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun save(account: Account) {
        logger.info("Persisting account: ${account.id} into database")

        transaction {
            AccountTable.insert {
                it[id] = account.id
                it[documentNumber] = account.documentNumber
                it[availableCreditLimit] = account.availableCreditLimit
            }
        }
    }

    override fun findById(id: UUID): Account? {
        logger.info("Searching for account: $id in database")

        return transaction {
            AccountTable
                .select { AccountTable.id eq id }
                .firstOrNull()
                ?.let { AccountTable.toAccount(it) }
        }
    }

    override fun findByDocumentNumber(documentNumber: String): Account? {
        logger.info("Searching for account with documentNumber: $documentNumber in database")

        return transaction {
            AccountTable
                .select { AccountTable.documentNumber eq id }
                .firstOrNull()
                ?.let { AccountTable.toAccount(it) }
        }
    }
}
