val app_group: String by project
val app_version: String by project

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
  application
  kotlin("jvm")
  kotlin("plugin.serialization")
}

group = app_group
version = app_version

application { mainClass.set("io.ktor.server.netty.EngineMain") }

repositories { mavenCentral() }

dependencies {
  // Project modules
  implementation(project(":core"))
  implementation(project(":port:db"))

  // Kotlin
  implementation(kotlin("stdlib"))

  // Ktor
  implementation("io.ktor:ktor-server-core:$ktor_version")
  implementation("io.ktor:ktor-locations:$ktor_version")
  implementation("io.ktor:ktor-serialization:$ktor_version")
  implementation("io.ktor:ktor-server-host-common:$ktor_version")
  implementation("io.ktor:ktor-auth:$ktor_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")

  // Test
  testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}
