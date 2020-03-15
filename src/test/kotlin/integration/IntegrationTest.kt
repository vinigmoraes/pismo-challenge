package integration

import br.com.pismochallenge.application.PismoChallengeApplication
import integration.containers.PostgresContainer
import io.ktor.server.engine.stop
import io.ktor.server.netty.NettyApplicationEngine
import org.junit.AfterClass
import org.junit.BeforeClass
import java.util.concurrent.TimeUnit

abstract class IntegrationTest {

    companion object {
        private lateinit var server: NettyApplicationEngine

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            PostgresContainer.start()
            server = PismoChallengeApplication.start(8081)
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            PostgresContainer.stop()
            server.stop(5, 10, TimeUnit.SECONDS)
        }
    }
}
