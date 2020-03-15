package br.com.pismochallenge.core.account.exceptions

import br.com.pismochallenge.commons.errors.ErrorResponse
import br.com.pismochallenge.commons.exceptions.PismoChallengeException
import io.ktor.http.HttpStatusCode
import java.util.UUID

class AccountNotFoundException(
    private val accountId: UUID
) : PismoChallengeException() {
    override fun response(): ErrorResponse  = ErrorResponse.create("Account with id: $accountId not found")

    override fun statusCode(): HttpStatusCode = HttpStatusCode.NotFound
}
