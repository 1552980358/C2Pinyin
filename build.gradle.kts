plugins {
    kotlin("jvm") version "1.9.22"
    id("maven-publish")
}

object ProjectProperties {

    val group: String
        get() = "org.ks.chan"

    val version: String
        get() = "3.0.0-rc1"

    val artifactId: String
        get() = "C2Pinyin"

}

group = ProjectProperties.group
version = ProjectProperties.version

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("Maven") {
            groupId = ProjectProperties.group
            artifactId = ProjectProperties.artifactId
            version = ProjectProperties.version
            from(components["kotlin"])
        }
    }
}