val kotlin_version: String by project
val kotest_version: String by project
val mockk_version: String by project
val ktor_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project
val hikari_cp_version: String by project
val postgres_version: String by project

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    kotlin("kapt") version "1.6.0"
    id("com.diffplug.spotless") version "5.15.1"
}

repositories { mavenCentral() }

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")

    repositories { mavenCentral() }

    dependencies {
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
        implementation("ch.qos.logback:logback-classic:$logback_version")

        implementation("io.ktor:ktor-server-auth:$ktor_version")
        implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")

        //database
        implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
        implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
        implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
        implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposed_version")
        implementation("com.h2database:h2:$h2_version")
        implementation("com.zaxxer:HikariCP:$hikari_cp_version")
        implementation("org.postgresql:postgresql:$postgres_version")

        // Testing
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
        testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
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
}
