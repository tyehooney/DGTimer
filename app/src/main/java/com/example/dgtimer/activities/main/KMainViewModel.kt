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

    private fun updateCapsulesFromServer() {
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

    init {
        updateCapsulesFromServer()
    }
}