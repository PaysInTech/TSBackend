val app_group: String by project
val app_version: String by project

val ktor_version: String by project
val kotlin_version: String by project
val dagger_version: String by project
val logback_version: String by project
val kotest_ktor_version: String by project
val kotest_testcontainers_version: String by project
val testcontainers_version: String by project
val liquibase_version: String by project
val firebase_adminsdk_version: String by project

plugins {
    application
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

group = app_group
version = app_version

application { mainClass.set("io.ktor.server.netty.EngineMain") }

repositories {
    mavenCentral()
    google()
}

dependencies {
    // Project modules
    implementation(project(":core"))
    implementation(project(":port:db"))

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

    // Ktor
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-locations:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.google.firebase:firebase-admin:$firebase_adminsdk_version")

    // Dagger
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")
    kaptTest("com.google.dagger:dagger-compiler:$dagger_version")

    // Test
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("io.kotest.extensions:kotest-assertions-ktor:$kotest_ktor_version")
    testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:$kotest_testcontainers_version")

    testImplementation("org.liquibase:liquibase-core:$liquibase_version")
    testImplementation("org.testcontainers:testcontainers:$testcontainers_version")
    testImplementation("org.testcontainers:postgresql:$testcontainers_version")
}

tasks.named<JavaExec>("run") {
    dependsOn(":migration:run")
}
