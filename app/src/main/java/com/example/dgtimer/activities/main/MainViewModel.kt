package com.example.dgtimer.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dgtimer.DGTimerPreferences
import com.example.dgtimer.PrefKey
import com.example.dgtimer.db.Capsule
import com.example.dgtimer.repo.CapsuleRepository
import com.example.dgtimer.utils.trimAllSpaces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CapsuleRepository,
    private val preferences: DGTimerPreferences
) : ViewModel() {

    private val _isInitialized: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isInitialized = _isInitialized.asStateFlow()

    var savedVersionCode = preferences.getInt(PrefKey.VersionCode)
        private set

    fun saveVersionCode(newVersionCode: Int) {
        savedVersionCode = newVersionCode
        preferences.put(PrefKey.VersionCode, newVersionCode)
    }

    val capsules: Flow<List<Capsule>?> = repository.loadCapsules()

    private val searchingWord: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchedCapsules: Flow<List<Capsule>> =
        capsules.combine(searchingWord) { capsules, word ->
            val trimmedWord = word.trimAllSpaces().lowercase()
            if (trimmedWord.isEmpty()) {
                emptyList()
            } else {
                capsules?.filter { capsule ->
                    capsule.name.trimAllSpaces().lowercase().contains(trimmedWord)
                } ?: emptyList()
            }
        }

    fun updateCapsulesFromServer() {
        repository.refreshCapsules {
            _isInitialized.value = true
        }
    }

    fun searchCapsules(text: String) {
        searchingWord.value = text
    }

    private val _isSearchModeOn: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isSearchModeOn = _isSearchModeOn.asStateFlow()
    fun setSearchMode(isOn: Boolean) {
        _isSearchModeOn.value = isOn
    }

    private var currentScrollY = 0
    private val _showFab: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showFab = _showFab.asStateFlow()
    fun setScrollYForShowingFab(dy: Int) {
        currentScrollY += dy
        val needToShowFab = currentScrollY > FAB_SCROLL_Y_LIMIT
        if (_showFab.value != needToShowFab) {
            _showFab.value = needToShowFab
        }
    }

    private val _updateCapsuleMajorEvent: MutableSharedFlow<Unit> = MutableSharedFlow()
    val updateCapsuleMajorEvent: SharedFlow<Unit> = _updateCapsuleMajorEvent.asSharedFlow()
    fun updateCapsuleMajor(capsuleId: Int) {
        viewModelScope.launch {
            repository.updateCapsuleMajor(capsuleId)
            _updateCapsuleMajorEvent.emit(Unit)
        }
    }

    companion object {
        private const val FAB_SCROLL_Y_LIMIT = 300
    }
}