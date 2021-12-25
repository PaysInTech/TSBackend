package app.paysintech.di.module

import app.paysintech.config.DatabaseConfig
import app.paysintech.db.DefaultDataSource
import dagger.Module
import dagger.Provides
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
}
