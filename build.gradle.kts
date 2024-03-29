import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

val kotlin_version: String by project
val kotest_version: String by project
val mockk_version: String by project
val coroutines_version: String by project

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    kotlin("kapt") version "1.6.0"
    id("com.diffplug.spotless") version "5.15.1"
    jacoco
}

repositories { mavenCentral() }

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "jacoco")

    repositories { mavenCentral() }

    jacoco {
        toolVersion = "0.8.7"
    }

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
        testLogging {
            events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
        }
    }

    kotlin {
        jvmToolchain {
            (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(14))
        }
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

    tasks.test {
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }
    tasks.jacocoTestReport {
        dependsOn(tasks.test) // tests are required to run before generating the report

        reports {
            xml.required.set(false)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }
}
