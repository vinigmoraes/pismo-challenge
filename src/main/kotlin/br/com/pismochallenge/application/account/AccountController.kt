package br.com.pismochallenge.application.account

import br.com.pismochallenge.application.account.response.AccountResponse
import br.com.pismochallenge.application.account.response.CreateAccountResponse
import br.com.pismochallenge.core.account.AccountService
import br.com.pismochallenge.core.account.ports.CreateAccountRequest
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import org.slf4j.LoggerFactory
import java.util.UUID

class AccountController(
    private val service: AccountService,
    private val mapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun create(call: ApplicationCall) {
        val json = call.receiveText()

        logger.info("Create account request received: $json")

        val request = mapper.readValue(json, CreateAccountRequest::class.java)

        val account = service.create(request)

        logger.info("Account: ${account.id} created successfully")

        call.respond(HttpStatusCode.Created, CreateAccountResponse.create(account.id))
    }

    suspend fun get(call: ApplicationCall) {
        val id = call.parameters["id"]!!

        logger.info("Get account: $id request received")

        val account = service.find(UUID.fromString(id))

        call.respond(HttpStatusCode.OK, AccountResponse.create(account))
    }
}
