plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.dcendents.android-maven") version "2.1"
}

group = "com.github.xuxiangjun"
version = "0.7.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
}
