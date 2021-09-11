package app.techsalaries.di.module

import app.techsalaries.config.DatabaseConfig
import app.techsalaries.db.DefaultDataSource
import dagger.Module
import dagger.Provides
import org.ktorm.database.Database
import javax.inject.Singleton
import javax.sql.DataSource

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun dataSource(databaseConfig: DatabaseConfig): DataSource = DefaultDataSource(
        databaseUrl = databaseConfig.url,
        databaseUser = databaseConfig.username,
        databasePassword = databaseConfig.password
    )

    @Provides
    @Singleton
    fun database(dataSource: DataSource): Database = Database.connect(dataSource)
}
