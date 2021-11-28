val javax_inject_version: String by project
val ktor_version: String by project
val firebase_adminsdk_version: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    // Core Module
    implementation(project(":core"))

    // JavaX Inject
    api("javax.inject:javax.inject:$javax_inject_version")

    // Firebase Dependencies
    api("com.google.firebase:firebase-admin:$firebase_adminsdk_version")

    // HTTP Client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")

}
