plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.dcendents.android-maven") version "1.5"
}

group = "com.github.xuxiangjun"
version = "0.1"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib"))
}
