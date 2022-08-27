package com.example.dgtimer

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DGTimerPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun getBoolean(prefKey: PrefKey, defaultValue: Boolean = false): Boolean =
        prefs.getBoolean(prefKey.value, defaultValue)

    fun getString(prefKey: PrefKey, defaultValue: String? = null): String? =
        prefs.getString(prefKey.value, defaultValue)

    fun getLong(prefKey: PrefKey, defaultValue: Long = -1L): Long =
        prefs.getLong(prefKey.value, defaultValue)

    fun getInt(prefKey: PrefKey, defaultValue: Int = -1): Int =
        prefs.getInt(prefKey.value, defaultValue)

    fun <T> put(prefKey: PrefKey, value: T) {
        val editor = prefs.edit()
        val key = prefKey.value
        when (value) {
            is Boolean -> {
                editor.putBoolean(key, value)
            }
            is String -> {
                editor.putString(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is Int -> {
                editor.putInt(key, value)
            }
        }
        editor.apply()
    }

    companion object {
        private const val PREF_NAME = "userOption"

        val vibratePattern = longArrayOf(0, 500, 500, 500)
        const val DEFAULT_AMPLITUDE = 180
        const val DEFAULT_VOLUME = 75
        const val DEFAULT_ALARM = 0
    }
}

sealed class PrefKey(val value: String) {
    object Alarm : PrefKey("alarm")
    object AlarmBell : PrefKey("alarmBell")
    object Amplitude : PrefKey("amplitude")
    object Volume : PrefKey("volume")
    object IsRaterShown : PrefKey("dontshowagain")
    object RaterLaunchCount : PrefKey("launch_count")
    object FirstLaunchedDate : PrefKey("date_firstlaunch")
    object VersionCode : PrefKey("currentVersionCode")
}