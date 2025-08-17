import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlinx.serialization)
}

group = "me.ks.chan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":mapping"))

    implementation(libs.jetbrains.kotlinx.serialization.json)
    testImplementation(libs.jetbrains.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    compilerOptions.jvmTarget = JvmTarget.JVM_21
    jvmToolchain(21)

    sourceSets.all {
        (properties["kotlin.experimental.languageFeatures"] ?: "")
            .let(Any::toString)
            .split(",")
            .filter(String::isNotBlank)
            .forEach(languageSettings::enableLanguageFeature)
    }
}
