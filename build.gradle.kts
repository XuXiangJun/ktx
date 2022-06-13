plugins {
    kotlin("jvm") version "1.6.21"
    id("maven-publish")
}

group = "com.github.XuXiangJun"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "com.github.XuXiangJun"
                artifactId = "kt-ext"
                version = "1.11.1"

                from(components["kotlin"])
            }
        }
    }
}