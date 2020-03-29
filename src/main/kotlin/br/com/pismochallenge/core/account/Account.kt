package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.exceptions.InsufficientBalanceException
import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import br.com.pismochallenge.core.transaction.OperationType
import java.math.BigDecimal
import java.util.UUID

data class Account(
    val id: UUID,
    val documentNumber: String,
    val availableCreditLimit: BigDecimal = BigDecimal(0)
) {

    companion object {
        fun create(request: CreateAccountRequest) =
            Account(
                id = UUID.randomUUID(),
                documentNumber = request.documentNumber
            )
    }

    fun updateBalance(amount: BigDecimal, operationType: OperationType): Account {
        if (operationType == OperationType.PAYMENT) {

            return copy(availableCreditLimit = availableCreditLimit + amount)
        }

        require(amount <= availableCreditLimit) { throw InsufficientBalanceException(id) }

        return copy(availableCreditLimit = availableCreditLimit - amount)
    }
}
