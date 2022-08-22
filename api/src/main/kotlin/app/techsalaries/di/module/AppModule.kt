package app.techsalaries.di.module

import app.techsalaries.JwtService
import app.techsalaries.config.DatabaseConfig
import dagger.Module
import dagger.Provides
import io.ktor.server.application.Application
import io.ktor.server.config.ApplicationConfig
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    fun applicationConfig(application: Application) = application.environment.config

    @Provides
    fun databaseConfig(config: ApplicationConfig): DatabaseConfig {
        val dbConfig = config.config("database")

        return DatabaseConfig(
            host = dbConfig.property("host").getString(),
            port = dbConfig.property("port").getString(),
            name = dbConfig.property("database").getString(),
            username = dbConfig.property("user").getString(),
            password = dbConfig.property("password").getString()
        )
    }

    @Provides
    @Singleton
    fun jwtService(): JwtService = JwtService()
}
