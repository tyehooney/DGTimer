package com.example.dgtimer.repo

import com.example.dgtimer.db.Capsule
import kotlinx.coroutines.flow.Flow

interface CapsuleRepository {
    fun refreshCapsules(onFinished: (Boolean) -> Unit)
    fun loadCapsules(): Flow<List<Capsule>?>
    suspend fun addCapsule(capsule: Capsule)
    suspend fun getCapsuleByName(name: String): List<Capsule>?
    suspend fun getStarredCapsules(): List<Capsule>?
    suspend fun getCapsuleById(id: Int): Capsule?
    suspend fun searchCapsulesByName(name: String): List<Capsule>?
    fun searchCapsuleById(id: Int): Flow<Capsule?>
    suspend fun updateCapsuleMajor(capsuleId: Int)
}