package br.com.pismochallenge.application.account.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class CreateAccountResponse(
    @get:JsonProperty("id")
    val id: UUID
) {

    companion object {
        fun create(id: UUID) = CreateAccountResponse(id = id)
    }
}
