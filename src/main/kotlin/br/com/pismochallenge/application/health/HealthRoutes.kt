package br.com.pismochallenge.application.health

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.health() {

    get("/health") { call.respond(HttpStatusCode.OK) }
}
