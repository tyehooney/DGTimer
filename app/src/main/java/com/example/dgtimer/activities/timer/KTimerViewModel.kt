package com.example.dgtimer.activities.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dgtimer.db.Capsule
import com.example.dgtimer.repo.CapsuleRepository
import com.example.dgtimer.utils.TimeUtils.stageToSecond
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KTimerViewModel @Inject constructor(
    private val repository: CapsuleRepository
): ViewModel() {

    var capsule: Capsule? = null

    private val _counters: MutableStateFlow<List<Counter>> =
        MutableStateFlow(emptyList())
    val counters = _counters.asStateFlow()

    var setCapsuleDataJob: Job? = null
    private var countDownTimerJob: Job? = null

    fun setCapsuleData(capsuleId: Int) {
        setCapsuleDataJob?.cancel()
        setCapsuleDataJob = viewModelScope.launch {
            val fetchedCapsule = repository.getCapsuleById(capsuleId) ?: return@launch
            capsule = fetchedCapsule
            val fetchedCounters = fetchedCapsule.stage.mapIndexed { index, stage ->
                Counter(
                    fetchedCapsule.type ?: "",
                    stageToSecond(stage),
                    index,
                    index == 0
                )
            }
            _counters.value = fetchedCounters
            setCapsuleDataJob = null
        }
    }

    fun setActiveCounter(activeIndex: Int) {
        _counters.value = counters.value.map {
            it.copy(isActive = it.index == activeIndex)
        }
    }
}