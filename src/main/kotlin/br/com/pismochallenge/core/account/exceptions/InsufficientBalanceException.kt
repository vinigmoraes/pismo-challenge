package br.com.pismochallenge.core.account.exceptions

import br.com.pismochallenge.commons.errors.ErrorResponse
import br.com.pismochallenge.commons.exceptions.PismoChallengeException
import io.ktor.http.HttpStatusCode
import java.util.UUID

class InsufficientBalanceException(
    private val id: UUID
) : PismoChallengeException() {

    override fun response(): ErrorResponse =
        ErrorResponse.create("Account with id: $id has insufficient balance for this operation")

    override fun statusCode(): HttpStatusCode = HttpStatusCode.UnprocessableEntity
}
