package app.techsalaries.db

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.FileSystemResourceAccessor
import org.junit.ClassRule
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer
import java.nio.file.Paths
import java.sql.DriverManager

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
