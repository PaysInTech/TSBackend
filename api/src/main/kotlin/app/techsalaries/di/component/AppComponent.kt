package app.techsalaries.di.component

import app.techsalaries.JwtService
import app.techsalaries.config.DatabaseConfig
import app.techsalaries.di.module.AppModule
import app.techsalaries.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import io.ktor.server.application.Application
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun app(): Application

    fun controllerComponent(): ControllerComponent

    fun jwtService(): JwtService

    fun databaseConfig(): DatabaseConfig

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder
        fun build(): AppComponent
    }
}
