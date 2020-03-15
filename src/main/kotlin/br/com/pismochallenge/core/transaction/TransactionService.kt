package br.com.pismochallenge.core.transaction

import br.com.pismochallenge.core.transaction.ports.TransactionRepository
import br.com.pismochallenge.core.transaction.ports.TransactionRequest

class TransactionService(
    private val repository: TransactionRepository
) {

    fun create(request: TransactionRequest) = Transaction.create(
            request
        )
        .also { repository.save(it) }
}
