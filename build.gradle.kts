val kotlin_version: String by project
val kotest_version: String by project
val mockk_version: String by project
val coroutines_version: String by project

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    kotlin("kapt") version "1.5.31"
    id("com.diffplug.spotless") version "6.0.1"
}

repositories { mavenCentral() }

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")

    repositories { mavenCentral() }

    dependencies {
        // Standard Library
        implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")

        // Testing
        testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
        testImplementation("io.kotest:kotest-assertions-core:$kotest_version")
        testImplementation("io.kotest:kotest-property:$kotest_version")
        testImplementation("io.mockk:mockk:$mockk_version")
    }

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")
            ktlint()
//        licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_14
        targetCompatibility = JavaVersion.VERSION_14
    }

    tasks.compileKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }

    tasks.compileTestKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }
}
