val javaVersion = JavaVersion.VERSION_17
val jacksonVersion = "2.13.4"
val ktorVersion = "2.1.1"
val mockkVersion = "1.13.1"

plugins {
    application
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("ca.cutterslade.analyze") version "1.9.0"
}

repositories {
    mavenCentral()
    maven("https://packages.confluent.io/maven/")
    maven("https://jitpack.io")
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-http-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jvm:$ktorVersion")
    implementation("io.ktor:ktor-utils-jvm:$ktorVersion")

    implementation("com.github.navikt:rapids-and-rivers:2022092314391663936769.9d5d33074875")

    implementation("net.logstash.logback:logstash-logback-encoder:7.2")
    implementation("ch.qos.logback:logback-classic:1.4.1")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")

    implementation("org.jetbrains:annotations:23.0.0")
    implementation("com.natpryce:konfig:1.6.10.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.mockk:mockk-dsl-jvm:$mockkVersion")
    testImplementation("org.skyscreamer:jsonassert:1.5.1")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion") {
        exclude(group = "junit")
        exclude(group = "org.eclipse.jetty") // conflicts with WireMock
        exclude(group = "org.eclipse.jetty.http2") // conflicts with WireMock
    }
}

configurations.all {
    // exclude JUnit 4
    exclude(group = "junit", module = "junit")
}

application {
    mainClass.set("no.nav.tiltakspenger.testmeldinger.ApplicationKt")
}

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$projectDir/config/detekt.yml")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = javaVersion.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = javaVersion.toString()
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
    test {
        // JUnit 5 support
        useJUnitPlatform()
        // https://phauer.com/2018/best-practices-unit-testing-kotlin/
        systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    }
    analyzeClassesDependencies {
        warnUsedUndeclared = true
        warnUnusedDeclared = true
    }
    analyzeTestClassesDependencies {
        warnUsedUndeclared = true
        warnUnusedDeclared = true
    }
}
