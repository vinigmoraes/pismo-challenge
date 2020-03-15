package br.com.pismochallenge.application.transaction

import br.com.pismochallenge.core.transaction.TransactionService
import br.com.pismochallenge.core.transaction.ports.TransactionRepository
import br.com.pismochallenge.infrastructure.transaction.TransactionRepositoryAdapter
import org.koin.dsl.module

val transactionModule = module {
    single { TransactionController(get(),get()) }
    single { TransactionService(get()) }
    single<TransactionRepository> { TransactionRepositoryAdapter() }
}
