plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.tyehooney.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(androidxLibs.core.ktx)
    implementation(androidxLibs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(androidxLibs.junit)
    androidTestImplementation(androidxLibs.espresso.core)

    // Room
    implementation(androidxLibs.room.runtime)
    implementation(androidxLibs.room.ktx)
    kapt(androidxLibs.room.compiler)
    annotationProcessor(androidxLibs.room.compiler)

    // hilt
    implementation(googleLibs.hilt.android)
    annotationProcessor(googleLibs.dagger.hilt.compiler)
    kapt(googleLibs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    // Google Firebase
    implementation(platform(googleLibs.firebase.bom))
    implementation(googleLibs.firebase.firestore.ktx)
    implementation(googleLibs.firebase.crashlytics.ktx)
    implementation(googleLibs.firebase.analytics.ktx)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
}