package br.com.pismochallenge.application.config

import com.typesafe.config.Config

class EnvironmentConfig(
    config: Config
) {
    val databaseUrl: String = config.getString("database.url")
    val databaseUsername: String = config.getString("database.username")
    val databasePassword: String = config.getString("database.password")
}

