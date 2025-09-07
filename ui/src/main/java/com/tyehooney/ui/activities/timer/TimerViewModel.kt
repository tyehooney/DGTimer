package com.tyehooney.ui.activities.timer

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.tyehooney.domain.model.Capsule
import com.tyehooney.domain.usecase.GetCapsuleByIdUseCase
import com.tyehooney.domain.usecase.UpdateCapsuleMajorUseCase
import com.tyehooney.domain.usecase.RefreshCapsulesUseCase
import com.tyehooney.ui.utils.TimeUtils.stageToMilliseconds
import com.tyehooney.data.DGTimerPreferences
import com.tyehooney.data.PrefKey
import com.tyehooney.data.DGTimerPreferences.Companion.DEFAULT_ALARM
import com.tyehooney.data.DGTimerPreferences.Companion.DEFAULT_AMPLITUDE
import com.tyehooney.data.DGTimerPreferences.Companion.DEFAULT_VOLUME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel 
class TimerViewModel @Inject constructor(
    private val getCapsuleByIdUseCase: GetCapsuleByIdUseCase,
    private val updateCapsuleMajorUseCase: UpdateCapsuleMajorUseCase,
    private val refreshCapsulesUseCase: RefreshCapsulesUseCase,
    private val preferences: DGTimerPreferences
) : ViewModel() {

    private val _capsuleFlow: MutableStateFlow<Capsule?> = MutableStateFlow(null)
    val capsuleFlow: StateFlow<Capsule?> = _capsuleFlow.asStateFlow()

    private val _counters: MutableStateFlow<List<Counter>> = MutableStateFlow(emptyList())
    val counters = _counters.asStateFlow()

    private var countDownTimer: CountDownTimer? = null
    private val _counterState = MutableStateFlow(CounterState.Ready)
    val counterState = _counterState.asStateFlow()

    private val _isAlarmOn = MutableStateFlow(
        preferences.getBoolean(PrefKey.AlarmBell, true)
    )
    val isAlarmOn: StateFlow<Boolean> = _isAlarmOn.asStateFlow()

    val alarmAmplitude get() = preferences.getInt(PrefKey.Amplitude, DEFAULT_AMPLITUDE)
    val alarmVolume get() = preferences.getInt(PrefKey.Volume, DEFAULT_VOLUME) / 100f
    val alarm get() = preferences.getInt(PrefKey.Alarm, DEFAULT_ALARM)

    fun refreshCapsuleData() {
        viewModelScope.launch {
            refreshCapsulesUseCase()
            val capsuleId = _capsuleFlow.value?.id
            capsuleId?.let {
                setCapsuleData(capsuleId)
            }
        }
    }

    fun setCapsuleData(capsuleId: Int) {
        viewModelScope.launch {
            val capsule = getCapsuleByIdUseCase(capsuleId)
            if (capsule != null) {
                _capsuleFlow.value = capsule
                
                // Create counters from capsule stages
                val counters = capsule.stage.mapIndexed { index, stage ->
                    Counter(
                        typeStringResId = capsule.typeStringResId,
                        totalTime = stageToMilliseconds(stage),
                        index = index,
                        isActive = index == 0,
                        currentTime = stageToMilliseconds(stage)
                    )
                }
                _counters.value = counters
            }
        }
    }

    fun toggleCapsuleMajor() {
        val capsuleId = _capsuleFlow.value?.id ?: return
        viewModelScope.launch {
            updateCapsuleMajorUseCase(capsuleId)
            _capsuleFlow.value = getCapsuleByIdUseCase(capsuleId)
        }
    }

    fun updateAlarmOn(isOn: Boolean) {
        preferences.put(PrefKey.AlarmBell, isOn)
        _isAlarmOn.value = isOn
    }

    fun setActiveCounter(activeIndex: Int) {
        if (activeIndex == getActiveCounter()?.index) return
        resetCountDownTimer()
        _counters.value = counters.value.map {
            it.copy(
                isActive = it.index == activeIndex,
                currentTime = it.totalTime
            )
        }
    }

    fun playCountDownTimer() {
        if (getActiveCounter()?.currentTime == 0L) {
            resetCountDownTimer()
        }
        val activeCounter = getActiveCounter() ?: return
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(activeCounter.currentTime, 100) {
            override fun onTick(millisUntilFinished: Long) {
                updateCounterTime(activeCounter.index, millisUntilFinished)
            }

            override fun onFinish() {
                _counterState.value = CounterState.Finished
                updateCounterTime(activeCounter.index, 0L)
                if (counters.value.size > 1 && 
                    activeCounter.index < counters.value.size - 1) {
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
        counters[index] = counter.copy(currentTime = timeInMillis)
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

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}