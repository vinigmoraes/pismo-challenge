package br.com.pismochallenge.core.account.ports

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateAccountRequest(
    @get:JsonProperty("document_number")
    val documentNumber: String
)
