import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlinx.serialization)
    `maven-publish`
}

group = "me.ks.chan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation(libs.jetbrains.kotlinx.serialization.json)

    compileOnly(libs.jetbrains.kotlin.stdlib)
    testImplementation(libs.jetbrains.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        jvmTarget = JvmTarget.JVM_21

        properties["kotlin.experimental.languageFeatures"]?.toString()
            .orEmpty()
            .split(",")
            .map(String::trim)
            .filter(String::isNotBlank)
            .map { "-X$it" }
            .forEach(freeCompilerArgs::add)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "c2pinyin"
            version = project.version.toString()

            from(components["java"])
        }
    }
}