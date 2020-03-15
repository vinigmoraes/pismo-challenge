package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import java.util.UUID

class Account(
    val id: UUID,
    val documentNumber: String
) {

    companion object {
        fun create(request: CreateAccountRequest) =
            Account(
                id = UUID.randomUUID(),
                documentNumber = request.documentNumber
            )
    }
}
