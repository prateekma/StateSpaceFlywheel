import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
}


repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    maven { setUrl("http://dl.bintray.com/kyonifer/maven") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.ghrobotics.FalconLibrary", "core", "2019.5.18")
    implementation("org.knowm.xchart", "xchart", "3.2.2")
    implementation("com.kyonifer", "koma-core-ejml", "0.12")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}