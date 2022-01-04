val liquibase_version: String by project
val postgres_version: String by project

plugins {
    application
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core:$liquibase_version")
    implementation("org.postgresql:postgresql:$postgres_version")
}

application {
    applicationName = "paysintech-migration"
    mainClass.set("liquibase.integration.commandline.Main")
}

distributions {
    main {
        contents {
            from("changelog")
        }
    }
}

tasks.named<JavaExec>("run") {

    val databaseHost = System.getenv("DATABASE_HOST") ?: "localhost"
    val databasePort = System.getenv("DATABASE_PORT") ?: "5432"
    val databaseName = System.getenv("DATABASE_NAME") ?: "paysintech_dev"
    val databaseUsername = System.getenv("DATABASE_USER") ?: "postgres"
    val databasePassword = System.getenv("DATABASE_PASSWORD") ?: "postgres"

    args = listOf(
        "--driver=org.postgresql.Driver",
        "--url=jdbc:postgresql://$databaseHost:$databasePort/$databaseName",
        "--username=$databaseUsername",
        "--password=$databasePassword",
        "--changeLogFile=changelog.xml",
        "update"
    )
}
