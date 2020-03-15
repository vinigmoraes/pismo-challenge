package integration.containers

import org.testcontainers.containers.FixedHostPortGenericContainer
import org.testcontainers.containers.wait.strategy.Wait

object PostgresContainer {

    private val container = Container("postgres")
        .withFixedExposedPort(5434, 5432)
        .withEnv("POSTGRES_DB", "pismo-challenge")
        .withEnv("POSTGRES_USER", "postgres")
        .withEnv("POSTGRES_PASSWORD", "root")
        .withCommand("-c max_connections=100")
        .waitingFor(Wait.forLogMessage(".*ready to accept connections.*\\n", 1))

    private var isRunning = false

    fun start() {
        if (isRunning.not()) {
            container.start()
            isRunning = true
        }
    }

    fun stop() {
        if (isRunning) {
            container.stop()
            isRunning = false
        }
    }
}

internal class Container(imageName: String) : FixedHostPortGenericContainer<Container>(imageName)
