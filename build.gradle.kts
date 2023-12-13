plugins {
    kotlin("jvm") version "1.9.10"
    id("maven-publish")
}

val kotlinVersion = "1.9.10"

val libGroup = "com.github.XuXiangJun"
val libArtifact = "ktx"
val libVersion = "2.1.0"

group = libGroup
version = libVersion

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = libGroup
            artifactId = libArtifact
            version = libVersion

            from(components["java"])
        }
    }
}
