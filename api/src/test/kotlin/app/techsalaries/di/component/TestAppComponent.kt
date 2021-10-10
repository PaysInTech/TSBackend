package app.techsalaries.di.component

import app.techsalaries.di.module.AppModule
import app.techsalaries.di.module.CoroutinesDispatchersModule
import app.techsalaries.di.module.RepositoryModule
import app.techsalaries.di.module.TestDatabaseModule
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
        CoroutinesDispatchersModule::class
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
