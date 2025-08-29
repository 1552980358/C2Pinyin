plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

group = "me.ks.chan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.jetbrains.kotlin.stdlib)
    testImplementation(kotlin("test"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}