plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.dcendents.android-maven") version "2.1"
}

group = "com.github.xuxiangjun"
version = "0.5.3"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.30")
}
