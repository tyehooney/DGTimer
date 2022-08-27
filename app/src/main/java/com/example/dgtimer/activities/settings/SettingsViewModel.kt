package com.example.dgtimer.activities.settings

import androidx.lifecycle.ViewModel
import com.example.dgtimer.DGTimerPreferences
import com.example.dgtimer.DGTimerPreferences.Companion.DEFAULT_ALARM
import com.example.dgtimer.DGTimerPreferences.Companion.DEFAULT_AMPLITUDE
import com.example.dgtimer.DGTimerPreferences.Companion.DEFAULT_VOLUME
import com.example.dgtimer.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: DGTimerPreferences
) : ViewModel() {

    private val _volume = MutableStateFlow(
        preferences.getInt(PrefKey.Volume, DEFAULT_VOLUME)
    )
    val volume = _volume.asStateFlow()
    fun setVolume(newVolume: Int) {
        if (newVolume == _volume.value) return
        _volume.value = newVolume
    }

    private val _amplitude = MutableStateFlow(
        preferences.getInt(PrefKey.Amplitude, DEFAULT_AMPLITUDE)
    )
    val amplitude = _amplitude.asStateFlow()
    fun setAmplitude(newAmplitude: Int) {
        if (newAmplitude == amplitude.value) return
        _amplitude.value = newAmplitude
    }

    private val _alarm = MutableStateFlow(
        preferences.getInt(PrefKey.Alarm, DEFAULT_ALARM)
    )
    val alarm = _alarm.asStateFlow()
    var selectedAlarmIndex = alarm.value
    fun setAlarm(newAlarm: Int) {
        if (newAlarm == alarm.value) return
        _alarm.value = newAlarm
    }

    fun resetAllSettings() {
        setVolume(DEFAULT_VOLUME)
        setAmplitude(DEFAULT_AMPLITUDE)
        setAlarm(DEFAULT_ALARM)
    }

    fun saveSettings() {
        with(preferences) {
            put(PrefKey.Volume, volume.value)
            put(PrefKey.Amplitude, amplitude.value)
            put(PrefKey.Alarm, alarm.value)
        }
    }
}