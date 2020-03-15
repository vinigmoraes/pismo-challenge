package br.com.pismochallenge.core.account

import br.com.pismochallenge.core.account.exceptions.AccountAlreadyExistException
import br.com.pismochallenge.core.account.exceptions.AccountNotFoundException
import br.com.pismochallenge.core.account.ports.AccountRepository
import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import java.util.UUID

class AccountServiceTest {

    private val repository = mockk<AccountRepository>()
    private val service = AccountService(repository)

    private val accountId = UUID.fromString("9adf6f9a-57bb-4bb9-9356-f6e8eec19977")
    private val documentNumber = "1893210"

    private val account = Account(
        id = accountId,
        documentNumber = documentNumber
    )

    @Test
    fun `given valid document number should create account successfully`() {
        val request = CreateAccountRequest(
            documentNumber = documentNumber
        )

        val expectedAccount = Account(
            id = accountId,
            documentNumber = documentNumber
        )

        every { repository.save(any()) } just runs
        every { repository.findByDocumentNumber(request.documentNumber) } returns null

        val account = service.create(request)

        assertThat(expectedAccount.documentNumber).isEqualTo(account.documentNumber)
    }

    @Test
    fun `given valid document number existent in database should throw exception`() {
        val request = CreateAccountRequest(
            documentNumber = "1893210"
        )

        every { repository.save(any()) } just runs
        every { repository.findByDocumentNumber(request.documentNumber) } returns account

        assertThrows(AccountAlreadyExistException::class.java) { service.create(request) }
    }

    @Test
    fun `given valid account id should return account successfully`() {
        val exceptedAccount = Account(
            id = accountId,
            documentNumber = documentNumber
        )

        every { repository.findById(accountId) } returns exceptedAccount

        val account = service.find(accountId)

        assertThat(exceptedAccount.id).isEqualTo(account.id)
        assertThat(exceptedAccount.documentNumber).isEqualTo(account.documentNumber)
    }

    @Test
    fun `given invalid account id should throw exception`() {
        val accountId = UUID.fromString("9adf6f9a-57bb-4bb9-9356-f6e8eec19977")

        every { repository.findById(accountId) } returns null

        assertThrows(AccountNotFoundException::class.java) { service.find(accountId) }
    }
}
