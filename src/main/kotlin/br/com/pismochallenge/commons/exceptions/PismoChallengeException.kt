package br.com.pismochallenge.commons.exceptions

import br.com.pismochallenge.commons.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

abstract class PismoChallengeException: RuntimeException() {

    abstract fun response(): ErrorResponse

    abstract fun statusCode(): HttpStatusCode
}
