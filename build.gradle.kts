plugins {
    kotlin("jvm") version "1.7.10"
    id("maven-publish")
}

val libGroup = "com.github.XuXiangJun"
val libVersion = "1.14.1"

group = libGroup
version = libVersion

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")
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
            artifactId = "kt-ext"
            version = libVersion

            from(components["java"])
        }
    }
}