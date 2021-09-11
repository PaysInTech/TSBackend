package app.techsalaries.di.component

import app.techsalaries.di.module.AppModule
import app.techsalaries.di.module.DatabaseModule
import app.techsalaries.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import io.ktor.application.Application
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {
    fun app(): Application

    fun controllerComponent(): ControllerComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder
        fun build(): AppComponent
    }
}
