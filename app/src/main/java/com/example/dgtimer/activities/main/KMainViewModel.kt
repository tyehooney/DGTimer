package com.example.dgtimer.activities.main

import androidx.lifecycle.ViewModel
import com.example.dgtimer.db.Capsule
import com.example.dgtimer.repo.CapsuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class KMainViewModel @Inject constructor(
    private val repository: CapsuleRepository
) : ViewModel() {
    val capsules: Flow<List<Capsule>?> = repository.loadCapsules()

    private val _searchedCapsules: MutableStateFlow<List<Capsule>?> =
        MutableStateFlow(emptyList())
    val searchedCapsules = _searchedCapsules.asStateFlow()

    fun updateCapsulesFromServer() {
        repository.refreshCapsules()
    }

    suspend fun searchCapsules(text: String) {
        _searchedCapsules.value = repository.searchCapsulesByName(text)
    }

    private val _isSearchModeOn: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isSearchModeOn = _isSearchModeOn.asStateFlow()
    fun setSearchMode(isOn: Boolean) {
        _isSearchModeOn.value = isOn
    }

    var currentScrollY = 0
        private set
    private val _showFab: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showFab = _showFab.asStateFlow()
    fun setScrollYForShowingFab(scrollY: Int) {
        currentScrollY += scrollY
        val needToShowFab = currentScrollY > FAB_SCROLL_Y_LIMIT
        if (_showFab.value != needToShowFab) {
            _showFab.value = needToShowFab
        }
    }

    fun updateCapsuleMajor(capsuleId: Int) {
        repository.updateCapsuleMajor(capsuleId)
    }

    companion object {
        private const val FAB_SCROLL_Y_LIMIT = 300
    }
}