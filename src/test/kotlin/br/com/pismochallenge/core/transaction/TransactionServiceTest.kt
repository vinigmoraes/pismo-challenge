package br.com.pismochallenge.core.transaction

import br.com.pismochallenge.core.transaction.ports.TransactionRepository
import br.com.pismochallenge.core.transaction.ports.TransactionRequest
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.assertj.core.api.Assertions.*
import org.junit.Test
import java.math.BigDecimal
import java.util.UUID

class TransactionServiceTest {

    private val repository = mockk<TransactionRepository>()
    private val service = TransactionService(repository)

    private val accountId = "9adf6f9a-57bb-4bb9-9356-f6e8eec19977"
    private val transactionID = "3475f98b-c6ce-4f42-b7f5-d981f85a5f58"

    @Test
    fun `given valid account and operation type should create transaction successfully`() {
        val request = TransactionRequest(
            accountId = accountId,
            operationTypeId = OperationType.WITHDRAWAL.value,
            amount = BigDecimal(10.5)
        )

        val expectedTransaction = Transaction(
            id = UUID.fromString(transactionID),
            accountId = accountId,
            operationTypeId = OperationType.WITHDRAWAL,
            amount = BigDecimal(10.5)
        )

        every { repository.save(any()) } just runs

        val transaction = service.create(request)

        assertThat(expectedTransaction.accountId).isEqualTo(transaction.accountId)
        assertThat(expectedTransaction.amount).isEqualTo(transaction.amount)
        assertThat(expectedTransaction.operationTypeId).isEqualTo(transaction.operationTypeId)
    }
}
