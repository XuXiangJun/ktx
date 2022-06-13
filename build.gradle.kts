plugins {
    kotlin("jvm") version "1.6.21"
    id("maven-publish")
}

group = "com.github.XuXiangJun"
version = "1.11.3"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
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
            groupId = "com.github.XuXiangJun"
            artifactId = "kt-ext"
            version = "1.11.3"

            from(components["java"])
        }
    }
}