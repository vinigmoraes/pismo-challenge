package br.com.pismochallenge.application.transaction.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class TransactionResponse(
    @get:JsonProperty("id")
    val id: UUID
) {

    companion object {
        fun create(id: UUID) = TransactionResponse(id = id)
    }
}
