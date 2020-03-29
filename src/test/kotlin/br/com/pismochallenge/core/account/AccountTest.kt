package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.exceptions.InsufficientBalanceException
import br.com.pismochallenge.core.transaction.OperationType
import br.com.pismochallenge.core.transaction.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import java.math.BigDecimal
import java.util.UUID

class AccountTest {

    private val account = Account(
        id = UUID.fromString("0607ad38-da5d-4e45-9e07-150771a0a640"),
        documentNumber = "9021831",
        availableCreditLimit = BigDecimal(500.0)
    )

    @Test
    fun `given operation type PAYMENT should increase balance successfully`() {
        val transaction = Transaction(
            id = UUID.randomUUID(),
            amount = BigDecimal(50.0),
            operationTypeId = OperationType.PAYMENT,
            accountId = "0607ad38-da5d-4e45-9e07-150771a0a640"
        )

        val expectedBalance = BigDecimal(550)

        val accountWithUpdatedBalance = account.updateBalance(transaction.amount, transaction.operationTypeId)

        assertThat(accountWithUpdatedBalance.availableCreditLimit).isEqualTo(expectedBalance)
    }

    @Test
    fun `given operation type CASH_PURCHASE should decrease balance successfully`() {
        val transaction = Transaction(
            id = UUID.randomUUID(),
            amount = BigDecimal(50.0),
            operationTypeId = OperationType.CASH_PURCHASE,
            accountId = "0607ad38-da5d-4e45-9e07-150771a0a640"
        )

        val expectedBalance = BigDecimal(450)

        val accountWithUpdatedBalance = account.updateBalance(transaction.amount, transaction.operationTypeId)

        assertThat(accountWithUpdatedBalance.availableCreditLimit).isEqualTo(expectedBalance)
    }

    @Test
    fun `given insufficient balance limit should throw exception`() {
        val transaction = Transaction(
            id = UUID.randomUUID(),
            amount = BigDecimal(600.0),
            operationTypeId = OperationType.CASH_PURCHASE,
            accountId = "0607ad38-da5d-4e45-9e07-150771a0a640"
        )

        assertThrows(InsufficientBalanceException::class.java) {
            account.updateBalance(
                transaction.amount,
                transaction.operationTypeId
            )
        }
    }
}
