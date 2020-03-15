package br.com.pismochallenge.application.transaction

import br.com.pismochallenge.application.transaction.response.TransactionResponse
import br.com.pismochallenge.core.transaction.TransactionService
import br.com.pismochallenge.core.transaction.ports.TransactionRequest
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import org.slf4j.LoggerFactory

class TransactionController(
    private val service: TransactionService,
    private val mapper: ObjectMapper
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun transactions(call: ApplicationCall) {
        val json = call.receiveText()

        logger.info("Receive create transaction request: $json")

        val request = mapper.readValue(json, TransactionRequest::class.java)

        val transaction = service.create(request)

        logger.info("Transaction created successfully: ${transaction.id}")

        call.respond(HttpStatusCode.Created, TransactionResponse.create(id = transaction.id))
    }
}
