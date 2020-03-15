package br.com.pismochallenge.application.account

import br.com.pismochallenge.core.account.AccountService
import br.com.pismochallenge.core.account.ports.AccountRepository
import br.com.pismochallenge.infrastructure.account.AccountRepositoryAdapter
import org.koin.dsl.module

val accountModule = module {
    single { AccountController(get(), get()) }
    single { AccountService(get()) }
    single<AccountRepository> { AccountRepositoryAdapter() }
}
