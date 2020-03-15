package br.com.pismochallenge.application.transaction

import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.transactions(controller: TransactionController) {

    post("/transactions") { controller.transactions(call) }
}
