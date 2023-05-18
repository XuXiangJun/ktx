plugins {
    kotlin("jvm") version "1.8.21"
    id("maven-publish")
}

val kotlinVersion = "1.8.21"

val libGroup = "com.github.XuXiangJun"
val libVersion = "1.16.0"

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
            artifactId = "kt-ext"
            version = libVersion

            from(components["java"])
        }
    }
}
