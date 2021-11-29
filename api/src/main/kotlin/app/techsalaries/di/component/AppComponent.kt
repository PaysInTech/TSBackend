package app.techsalaries.di.component

import app.techsalaries.api.authentication.Authenticator
import app.techsalaries.di.module.AppModule
import app.techsalaries.di.module.AuthModule
import app.techsalaries.di.module.CoroutinesDispatchersModule
import app.techsalaries.di.module.DatabaseModule
import app.techsalaries.di.module.FirebaseModule
import app.techsalaries.di.module.HttpClientModule
import app.techsalaries.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import io.ktor.application.Application
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        CoroutinesDispatchersModule::class,
        FirebaseModule::class,
        AuthModule::class,
        HttpClientModule::class
    ]
)
interface AppComponent {
    fun app(): Application

    fun controllerComponent(): ControllerComponent

    fun authenticator(): Authenticator

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder
        fun build(): AppComponent
    }
}
