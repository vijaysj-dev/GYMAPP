// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven") } // Chaquopy repository
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")  // Your Android Gradle plugin version
        classpath("com.chaquo.python:gradle:12.0.0")  // Chaquopy plugin version
    }
}
