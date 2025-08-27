import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlinx.serialization) apply false
    `maven-publish`
}

group = "me.ks.chan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    subprojects.forEach { subproject ->
        implementation(subproject)
    }

    testImplementation(libs.jetbrains.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    compilerOptions.jvmTarget = JvmTarget.JVM_21
    jvmToolchain(21)

    sourceSets.all {
        properties["kotlin.experimental.languageFeatures"]?.toString()
            .orEmpty()
            .split(",")
            .filter(String::isNotBlank)
            .forEach(languageSettings::enableLanguageFeature)
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