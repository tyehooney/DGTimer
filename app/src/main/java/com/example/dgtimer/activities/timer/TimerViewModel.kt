package com.example.dgtimer.activities.timer

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dgtimer.DGTimerPreferences
import com.example.dgtimer.DGTimerPreferences.Companion.DEFAULT_AMPLITUDE
import com.example.dgtimer.DGTimerPreferences.Companion.DEFAULT_VOLUME
import com.example.dgtimer.PrefKey
import com.example.dgtimer.db.Capsule
import com.example.dgtimer.repo.CapsuleRepository
import com.example.dgtimer.utils.TimeUtils.stageToMilliseconds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val repository: CapsuleRepository,
    private val preferences: DGTimerPreferences
) : ViewModel() {

    private val _capsuleFlow: MutableStateFlow<Capsule?> =
        MutableStateFlow(null)
    val capsuleFlow: StateFlow<Capsule?> = _capsuleFlow.asStateFlow()

    private val _counters: MutableStateFlow<List<Counter>> =
        MutableStateFlow(emptyList())
    val counters = _counters.asStateFlow()

    val alarmAmplitude get() = preferences.getInt(PrefKey.Amplitude, DEFAULT_AMPLITUDE)
    val alarmVolume get() = preferences.getInt(PrefKey.Volume, DEFAULT_VOLUME) / 100f
    val alarm get() = preferences.getInt(PrefKey.Alarm, 0)

    private var countDownTimer: CountDownTimer? = null
    private val _counterState = MutableStateFlow(CounterState.Ready)
    val counterState = _counterState.asStateFlow()

    fun refreshCapsuleData() {
        repository.refreshCapsules {
            val capsuleId = _capsuleFlow.value?.id
            capsuleId?.let {
                setCapsuleData(capsuleId)
            }
        }
    }

    fun setCapsuleData(capsuleId: Int) {
        viewModelScope.launch {
            val fetchedCapsule = repository.getCapsuleById(capsuleId) ?: return@launch
            _capsuleFlow.value = fetchedCapsule
            val fetchedCounters = fetchedCapsule.stage.mapIndexed { index, stage ->
                Counter(
                    fetchedCapsule.typeStringResId,
                    stageToMilliseconds(stage),
                    index,
                    index == 0
                )
            }
            _counters.value = fetchedCounters
        }
    }

    fun toggleCapsuleMajor() {
        val capsuleId = _capsuleFlow.value?.id ?: return
        viewModelScope.launch {
            repository.updateCapsuleMajor(capsuleId)
            _capsuleFlow.value = repository.getCapsuleById(capsuleId)
        }
    }

    fun setActiveCounter(activeIndex: Int) {
        if (activeIndex == getActiveCounter()?.index) return
        resetCountDownTimer()
        _counters.value = counters.value.map {
            it.copy(
                isActive = it.index == activeIndex
            )
        }
    }

    fun playCountDownTimer() {
        if (getActiveCounter()?.currentTime == 0L) {
            resetCountDownTimer()
        }
        val activeCounter = getActiveCounter() ?: return
        countDownTimer?.cancel()
        countDownTimer =
            object : CountDownTimer(activeCounter.currentTime, 100) {
                override fun onTick(millisUntilFinished: Long) {
                    updateCounterTime(activeCounter.index, millisUntilFinished)
                }

                override fun onFinish() {
                    _counterState.value = CounterState.Finished
                    updateCounterTime(activeCounter.index, 0L)
                    if (
                        counters.value.size > 1 &&
                        activeCounter.index < counters.value.size - 1
                    ) {
                        setActiveCounter(activeCounter.index + 1)
                    }
                }
            }
        _counterState.value = CounterState.Counting
        countDownTimer?.start()
    }

    private fun updateCounterTime(index: Int, timeInMillis: Long) {
        val counters = counters.value.toMutableList()
        val counter = counters[index]
        counters[index] =
            counter.copy(
                currentTime = timeInMillis
            )
        _counters.value = counters
    }

    fun pauseCountDownTimer() {
        _counterState.value = CounterState.Paused
        countDownTimer?.cancel()
    }

    fun resetCountDownTimer() {
        countDownTimer?.cancel()
        _counterState.value = CounterState.Ready
        val activeCounter = getActiveCounter() ?: return
        _counters.value = counters.value.map {
            if (it.index == activeCounter.index) {
                it.copy(currentTime = it.totalTime)
            } else it
        }
    }

    private fun getActiveCounter(): Counter? =
        counters.value.firstOrNull { it.isActive }

    private val _isAlarmOn = MutableStateFlow(
        preferences.getBoolean(PrefKey.AlarmBell, true)
    )
    val isAlarmOn = _isAlarmOn.asStateFlow()

    fun updateAlarmOn(isOn: Boolean) {
        preferences.put(PrefKey.AlarmBell, isOn)
        _isAlarmOn.value = isOn
    }
}