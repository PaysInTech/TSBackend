package app.paysintech.di.component

import app.paysintech.api.authentication.Authenticator
import app.paysintech.di.module.AppModule
import app.paysintech.di.module.AuthModule
import app.paysintech.di.module.CoroutinesDispatchersModule
import app.paysintech.di.module.DatabaseModule
import app.paysintech.di.module.FirebaseModule
import app.paysintech.di.module.RepositoryModule
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
        AuthModule::class
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
