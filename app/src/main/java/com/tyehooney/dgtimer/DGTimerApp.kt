package com.tyehooney.dgtimer

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DGTimerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase Crashlytics
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        // Set custom keys for debugging (optional)
        FirebaseCrashlytics.getInstance().setCustomKey("app_version", BuildConfig.VERSION_NAME)
        FirebaseCrashlytics.getInstance()
            .setCustomKey("build_type", if (BuildConfig.DEBUG) "debug" else "release")
    }
}