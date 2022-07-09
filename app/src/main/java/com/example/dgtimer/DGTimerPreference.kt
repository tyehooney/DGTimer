package com.example.dgtimer

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DGTimerPreference @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun getBoolean(prefKey: PrefKey): Boolean =
        prefs.getBoolean(prefKey.value, false)

    fun getString(prefKey: PrefKey): String? =
        prefs.getString(prefKey.value, null)

    fun getLong(prefKey: PrefKey): Long =
        prefs.getLong(prefKey.value, -1L)

    fun getInt(prefKey: PrefKey): Int =
        prefs.getInt(prefKey.value, -1)

    companion object {
        private const val PREF_NAME = "userOption"
    }
}

sealed class PrefKey(val value: String) {
    class Alarm : PrefKey("alarm")
    class AlarmBell : PrefKey("alarmBell")
    class Amplitude : PrefKey("amplitude")
    class Volume : PrefKey("volume")
    class IsShown : PrefKey("dontshowagain")
    class LaunchCount : PrefKey("launch_count")
    class FirstLaunchedData : PrefKey("date_firstlaunch")
    class VersionCode : PrefKey("currentVersionCode")
}