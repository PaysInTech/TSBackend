val javax_inject_version: String by project
val kotlin_serializer_version: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    // JavaX Inject
    api("javax.inject:javax.inject:$javax_inject_version")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serializer_version")
}
