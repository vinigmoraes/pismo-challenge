package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.exceptions.AccountAlreadyExistException
import br.com.pismochallenge.core.account.exceptions.AccountNotFoundException
import br.com.pismochallenge.core.account.ports.AccountRepository
import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import org.slf4j.LoggerFactory
import java.util.UUID

class AccountService(
    private val repository: AccountRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun create(request: CreateAccountRequest): Account {
        logger.info("Creating account with info: $request")

        require(repository.findByDocumentNumber(request.documentNumber) == null) {
            throw AccountAlreadyExistException(request.documentNumber)
        }

        return Account
            .create(request)
            .also { repository.save(it) }
    }

    fun find(id: UUID) = repository.findById(id) ?: throw AccountNotFoundException(id)
}

