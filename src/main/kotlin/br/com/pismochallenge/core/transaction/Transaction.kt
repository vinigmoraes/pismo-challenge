package br.com.pismochallenge.core.transaction

import br.com.pismochallenge.core.transaction.ports.TransactionRequest
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class Transaction(
    val id: UUID,
    val accountId: String,
    val amount: BigDecimal
) {

    val createdAt: LocalDateTime = LocalDateTime.now()

    companion object {
        fun create(request: TransactionRequest) =
            Transaction(
                id = UUID.randomUUID(),
                accountId = request.accountId,
                amount = request.amount
            )
    }
}
