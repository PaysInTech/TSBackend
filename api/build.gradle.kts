val app_group: String by project
val app_version: String by project

val ktor_version: String by project
val kotlin_version: String by project
val dagger_version: String by project
val logback_version: String by project

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

    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-freemarker:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-resources:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-caching-headers:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")

    // Dagger
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")

    // Test
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

tasks.named<JavaExec>("run") {
    dependsOn(":migration:run")
}
