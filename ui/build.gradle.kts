plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(googleLibs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.tyehooney.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    implementation(project(":data"))
    implementation(project(":common"))

    // Android
    implementation(androidxLibs.core.ktx)
    implementation(androidxLibs.appcompat)
    implementation(androidxLibs.constraintlayout)
    implementation(libs.material)
    implementation(androidxLibs.activity.ktx)
    implementation(androidxLibs.recyclerview)

    // Lifecycle components
    implementation(androidxLibs.lifecycle.extensions)
    // kapt(androidxLibs.lifecycle.compiler)

    // Glide for image loading
    implementation(extLibs.glide)
    // kapt(extLibs.glide.compiler)

    // Kakao AdFit
    implementation(extLibs.kakao.ads.base)

    // Hilt
    implementation(googleLibs.hilt.android)
    kapt(googleLibs.dagger.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(androidxLibs.junit)
    androidTestImplementation(androidxLibs.espresso.core)
}