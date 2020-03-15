package br.com.pismochallenge.application.config

import com.typesafe.config.ConfigFactory
import org.koin.dsl.module
import javax.sql.DataSource

val configModule = module {
    single { ObjectMapperProvider.provide() }
    single { EnvironmentConfig(ConfigFactory.load()) }
    single<DataSource> {
        with(get<EnvironmentConfig>()) {
            DataSourceProvider.provide(databaseUrl, databaseUsername, databasePassword)
        }
    }
}
