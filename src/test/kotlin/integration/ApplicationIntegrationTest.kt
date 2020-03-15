package integration

import br.com.pismochallenge.commons.extensions.json
import br.com.pismochallenge.core.account.Account
import br.com.pismochallenge.infrastructure.account.AccountRepositoryAdapter
import com.github.kittinunf.fuel.Fuel
import integration.utils.readJson
import io.ktor.http.HttpStatusCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.UUID

class ApplicationIntegrationTest : IntegrationTest() {

    private val accountRepository = AccountRepositoryAdapter()

    private val accountId = "0607ad38-da5d-4e45-9e07-150771a0a640"
    private val documentNumber = "1928321"
    private val baseUrl = "http://localhost:8081"

    private val account = Account(
        id = UUID.fromString(accountId),
        documentNumber = documentNumber
    )

    @Test
    fun `given valid create account request should create account successfully`() {
        val request = readJson("request/account/create_account")

        val (_, response, _) = Fuel.post("$baseUrl/accounts")
            .body(request)
            .response()

        assertThat(response.statusCode).isEqualTo(HttpStatusCode.Created.value)
    }

    @Test
    fun `given valid account id should return account successfully`() {
        accountRepository.save(account)

        val expectedResponse = readJson("response/account/get_account_info")

        val (_, response, _) = Fuel.get("$baseUrl/accounts/$accountId")
            .response()

        assertThat(expectedResponse).isEqualToIgnoringWhitespace(response.json)
        assertThat(response.statusCode).isEqualTo(HttpStatusCode.OK.value)
    }

    @Test
    fun `given valid transaction request should create transaction successfully`() {
        val request = readJson("/request/transaction/create_transaction")

        val (_, response, _) = Fuel.post("$baseUrl/transactions")
            .body(request)
            .response()

        assertThat(response.statusCode).isEqualTo(HttpStatusCode.Created.value)
    }
}
