plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(googleLibs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.tyehooney.common"
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
    implementation(androidxLibs.core.ktx)
    
    // Hilt for dependency injection
    implementation(googleLibs.hilt.android)
    kapt(googleLibs.dagger.hilt.compiler)
    
    testImplementation(libs.junit)
    androidTestImplementation(androidxLibs.junit)
    androidTestImplementation(androidxLibs.espresso.core)
}