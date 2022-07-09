package com.example.dgtimer.activities.main

import androidx.lifecycle.ViewModel
import com.example.dgtimer.db.Capsule
import com.example.dgtimer.repo.CapsuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class KMainViewModel @Inject constructor(
    private val repository: CapsuleRepository
) : ViewModel() {
    val capsules: Flow<List<Capsule>?> = repository.loadCapsules()

    private fun updateCapsulesFromServer() {
        repository.refreshCapsules()
    }

    init {
        updateCapsulesFromServer()
    }
}