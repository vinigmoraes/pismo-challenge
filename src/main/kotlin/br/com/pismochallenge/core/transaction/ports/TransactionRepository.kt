package br.com.pismochallenge.core.transaction.ports

import br.com.pismochallenge.core.transaction.Transaction

interface TransactionRepository {

    fun save(transaction: Transaction)
}
