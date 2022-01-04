package app.paysintech.di.component

import app.paysintech.di.module.AppModule
import app.paysintech.di.module.CoroutinesDispatchersModule
import app.paysintech.di.module.RepositoryModule
import app.paysintech.di.module.TestAuthModule
import app.paysintech.di.module.TestDatabaseModule
import app.paysintech.di.module.TestFirebaseModule
import dagger.BindsInstance
import dagger.Component
import io.ktor.application.Application
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
@Component(
    modules = [
        AppModule::class,
        TestDatabaseModule::class,
        RepositoryModule::class,
        CoroutinesDispatchersModule::class,
        TestAuthModule::class,
        TestFirebaseModule::class
    ]
)
interface TestAppComponent : AppComponent {

    fun dataSource(): DataSource

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder
        fun build(): TestAppComponent
    }
}
