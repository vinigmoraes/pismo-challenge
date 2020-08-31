package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import java.math.BigDecimal
import java.util.UUID

data class Account(
    val documentNumber: String,
    val availableCreditLimit: BigDecimal = BigDecimal(0)
) {

    var id: UUID = UUID.randomUUID()

    companion object {
        fun create(request: CreateAccountRequest) = Account(
                documentNumber = request.documentNumber
            )
    }
}
