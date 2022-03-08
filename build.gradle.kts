plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.dcendents.android-maven") version "2.1"
}

group = "com.github.XuXiangJun"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
}
