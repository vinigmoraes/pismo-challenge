package br.com.pismochallenge.core.transaction

import br.com.pismochallenge.core.account.AccountService
import br.com.pismochallenge.core.transaction.ports.TransactionRepository
import br.com.pismochallenge.core.transaction.ports.TransactionRequest
import java.util.UUID

class TransactionService(
    private val repository: TransactionRepository,
    private val accountService: AccountService
) {

    fun create(request: TransactionRequest): Transaction {
        val transaction = Transaction.create(request)

        accountService.updateBalance(
            UUID.fromString(transaction.accountId),
            transaction.amount,
            transaction.operationTypeId
        )

        repository.save(transaction)

        return transaction
    }
}
