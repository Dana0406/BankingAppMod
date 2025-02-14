buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val nav_version = "2.7.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    kotlin("kapt") version "1.7.20"
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    //id("com.android.library") version "8.1.4" apply false
}