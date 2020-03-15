package br.com.pismochallenge.core.transaction.ports

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TransactionRequest(
    @get:JsonProperty("account_id")
    val accountId: String,
    @get:JsonProperty("operation_type_id")
    val operationTypeId: String,
    @get:JsonProperty("amount")
    val amount: BigDecimal
)
