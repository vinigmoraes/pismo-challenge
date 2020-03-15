package br.com.pismochallenge.application.account.response

import br.com.pismochallenge.core.account.Account
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class AccountResponse(
    @get:JsonProperty("account_id")
    val accountId: UUID,
    @get:JsonProperty("document_number")
    val documentNumber: String
) {

    companion object {
        fun create(account: Account) = AccountResponse(
            accountId = account.id,
            documentNumber = account.documentNumber
        )
    }
}
