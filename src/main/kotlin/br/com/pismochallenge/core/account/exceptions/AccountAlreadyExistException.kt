package br.com.pismochallenge.core.account.exceptions

import br.com.pismochallenge.commons.errors.ErrorResponse
import br.com.pismochallenge.commons.exceptions.PismoChallengeException
import io.ktor.http.HttpStatusCode

class AccountAlreadyExistException(
    private val documentNumber: String
) : PismoChallengeException() {

    override fun response(): ErrorResponse = ErrorResponse.create("Account already exist for documentNumber: $documentNumber")

    override fun statusCode(): HttpStatusCode  = HttpStatusCode.UnprocessableEntity
}
