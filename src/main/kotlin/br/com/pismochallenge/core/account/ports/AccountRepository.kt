package br.com.pismochallenge.core.account.ports

import br.com.pismochallenge.core.account.Account
import java.util.UUID

interface AccountRepository {

    fun save(account: Account)

    fun findById(id: UUID): Account?

    fun findByDocumentNumber(documentNumber: String): Account?
}
