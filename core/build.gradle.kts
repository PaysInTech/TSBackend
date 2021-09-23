val javax_inject_version: String by project

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    // JavaX Inject
    api("javax.inject:javax.inject:$javax_inject_version")
}
