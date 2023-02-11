plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.github.vnord.derdiedas"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    namespace = "com.github.vnord.derdiedas"

    buildTypes {
        debug {
            enableAndroidTestCoverage = true
        }
    }

    testOptions {
        animationsDisabled = true
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

ktlint {
    android.set(true)
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    // Compose
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.3")
    implementation("androidx.compose.material:material-icons-core:1.3.1")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // Room
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // test
    testImplementation("junit:junit:4.13.2")
    debugImplementation("androidx.test.ext:junit:1.1.5")
    debugImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation("org.assertj:assertj-core:3.4.1")
    debugImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    debugImplementation("androidx.navigation:navigation-testing:2.5.3")
    debugImplementation("androidx.fragment:fragment-testing:1.5.5")
}
