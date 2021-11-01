package app.techsalaries.di.module

import dagger.Module
import dagger.Provides
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.FileSystemResourceAccessor
import org.junit.ClassRule
import org.ktorm.database.Database
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer
import java.nio.file.Paths
import java.sql.DriverManager
import javax.inject.Singleton
import javax.sql.DataSource

@Module
object TestDatabaseModule {
    @Provides
    fun dataSource(): DataSource = TestDataSource()

    @Provides
    @Singleton
    fun database(dataSource: DataSource): Database = Database.connect(dataSource)
}

class TechSalariesPSQLContainer : PostgreSQLContainer<TechSalariesPSQLContainer>("postgres")

class TestDataSource : PGSimpleDataSource() {

    @ClassRule
    private val sqlContainer = TechSalariesPSQLContainer().also { it.start() }

    init {
        val connection = DriverManager.getConnection(sqlContainer.jdbcUrl, sqlContainer.username, sqlContainer.password)

        Liquibase(
            "../migration/src/main/resources/changelog.xml",
            FileSystemResourceAccessor(Paths.get(".").toFile()),
            JdbcConnection(connection)
        ).update("main")

        setURL(sqlContainer.jdbcUrl)
        user = sqlContainer.username
        password = sqlContainer.password
    }
}
