package br.com.pismochallenge.application

import br.com.pismochallenge.application.account.accountModule
import br.com.pismochallenge.application.account.accounts
import br.com.pismochallenge.application.config.ObjectMapperProvider
import br.com.pismochallenge.application.config.configModule
import br.com.pismochallenge.application.health.health
import br.com.pismochallenge.application.transaction.transactionModule
import br.com.pismochallenge.application.transaction.transactions
import br.com.pismochallenge.commons.exceptions.PismoChallengeException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.jackson.JacksonConverter
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import javax.sql.DataSource

fun main() {
    embeddedServer(Netty, 8080) {
        main()
    }.start(wait = true)
}

object PismoChallengeApplication {

    fun start(port: Int): NettyApplicationEngine = embeddedServer(
        factory = Netty,
        watchPaths = listOf("src/main/kotlin/br.com.pismochallenge"),
        port = port,
        module = Application::main
    ).start(false)
}

fun Application.main() {

    install(Koin) {
        modules(listOf(configModule, accountModule, transactionModule))
    }

    install(StatusPages) {
        exception<PismoChallengeException> { call.respond(it.statusCode(), it.response()) }
    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(ObjectMapperProvider.provide()))
    }

    startDatabase(get())

    routing {
        health()
        accounts(get())
        transactions(get())
    }
}

private fun startDatabase(dataSource: DataSource) {
    Flyway
        .configure()
        .dataSource(dataSource)
        .load()
        .migrate()
    Database.connect(dataSource)
}


