package com.splanes.grocery.ms_customer.infraestructure.db.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
open class DBConfig {

    @Bean
    open fun dbConnectionFactoryInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(ResourceDatabasePopulator(*dbResources()))
        }

    private fun dbResources(): Array<ClassPathResource> =
        arrayOf(
            ClassPathResource("db/v1/schema.sql"),
            ClassPathResource("db/v1/debug_population.sql"),
            ClassPathResource("db/v1/population.sql"),
        )
}