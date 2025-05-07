import org.gradle.internal.impldep.org.apache.ivy.util.url.IvyAuthenticator.install

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.chaquo.python") // Chaquopy plugin
}

android {
    flavorDimensions += "pyVersion"
    productFlavors {
        create("py310") { dimension = "pyVersion" }

    }
    namespace = "com.example.learning_rate"
    compileSdk = 35

    defaultConfig {



        applicationId = "com.example.learning_rate"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64") // ✅ This is correct syntax in Kotlin DSL
        }



//        python {
//            // Optional: specify the path to your Python interpreter (only if not auto-detected)
//            //buildPython = "C:\\Users\\YourUsername\\AppData\\Local\\Programs\\Python\\Python310\\python.exe"
//
//            pip {
//                install("numpy") // Install numpy and any other libraries you need
//            }
//        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
}
chaquopy {
    productFlavors {
        getByName("py310") { version = "3.10" }

    }
    defaultConfig {
        version = "3.10"
        pip {
            install("numpy")
            //install("opencv-python") this damn thing works on c++ dependencies and the gradle
            //cry everytime i sync
        }
    }
    sourceSets {
        getByName("main") {
            srcDir("src/main/python")
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    val camerax_version = "1.2.2"
    implementation("androidx.camera:camera-core:${camerax_version}")
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation("androidx.camera:camera-video:${camerax_version}")
    implementation("androidx.camera:camera-view:${camerax_version}")
    implementation("androidx.camera:camera-extensions:${camerax_version}")

    //implementation("com.google.mlkit:face-detection:16.1.5")
    //implementation("com.chaquo.python:chaquopy:12.0.0")

    implementation("com.google.mediapipe:tasks-vision:0.10.7")  // or latest

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
