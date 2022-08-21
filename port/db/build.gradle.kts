val javax_inject_version: String by project
val exposed_version: String by project
val h2_version: String by project
val hikari_cp_version: String by project
val postgres_version: String by project

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

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposed_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("com.zaxxer:HikariCP:$hikari_cp_version")
    implementation("org.postgresql:postgresql:$postgres_version")
}
