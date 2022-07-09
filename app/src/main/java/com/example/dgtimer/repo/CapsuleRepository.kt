package com.example.dgtimer.repo

import com.example.dgtimer.db.Capsule
import kotlinx.coroutines.flow.Flow

interface CapsuleRepository {
    fun refreshCapsules()
    fun loadCapsules(): Flow<List<Capsule>?>
    fun addCapsule(capsule: Capsule)
    fun getCapsuleByName(name: String): List<Capsule>?
    fun getCapsuleById(id: Int): Capsule?
    fun searchCapsulesByName(name: String): List<Capsule>?
    fun searchCapsuleById(id: Int): Flow<Capsule?>
    fun updateCapsule(capsule: Capsule)
}