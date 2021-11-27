val javax_inject_version: String by project
val postgres_version: String by project
val ktorm_version: String by project
val hikari_version: String by project
val testcontainers_version: String by project
val liquibase_version: String by project

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    // Core Module
    implementation(project(":core"))

    // JavaX Inject
    api("javax.inject:javax.inject:$javax_inject_version")

    // PostgreSQL
    api("org.postgresql:postgresql:$postgres_version")

    // Ktorm
    api("org.ktorm:ktorm-core:$ktorm_version")
    api("org.ktorm:ktorm-support-postgresql:$ktorm_version")

    // Hikari
    api("com.zaxxer:HikariCP:$hikari_version")

    testImplementation("org.liquibase:liquibase-core:$liquibase_version")
    testImplementation("org.testcontainers:testcontainers:$testcontainers_version")
    testImplementation("org.testcontainers:postgresql:$testcontainers_version")
}
