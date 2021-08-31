plugins {
    kotlin("jvm") version "1.5.30"
}

group = "com.github.xuxiangjun"
version = "0.5.1"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.30")
}
