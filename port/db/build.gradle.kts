val javax_inject_version: String by project
val postgres_version: String by project
val ktorm_version: String by project
val hikari_version: String by project

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

    // Hikari
    api("com.zaxxer:HikariCP:$hikari_version")
}