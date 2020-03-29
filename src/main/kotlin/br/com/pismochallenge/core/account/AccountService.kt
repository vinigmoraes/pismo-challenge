package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.exceptions.AccountAlreadyExistException
import br.com.pismochallenge.core.account.exceptions.AccountNotFoundException
import br.com.pismochallenge.core.account.ports.AccountRepository
import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import br.com.pismochallenge.core.transaction.OperationType
import java.math.BigDecimal
import java.util.UUID

class AccountService(
    private val repository: AccountRepository
) {

    fun create(request: CreateAccountRequest): Account {
        require(repository.findByDocumentNumber(request.documentNumber) == null) {
            throw AccountAlreadyExistException(request.documentNumber)
        }

        return Account
            .create(request)
            .also { repository.save(it) }
    }

    fun find(id: UUID) = repository.findById(id) ?: throw AccountNotFoundException(id)

    fun updateBalance(accountId: UUID, amount: BigDecimal, operationType: OperationType) {
        val account = find(accountId)

        account
            .updateBalance(amount, operationType)
            .let { repository.save(it) }
    }
}

