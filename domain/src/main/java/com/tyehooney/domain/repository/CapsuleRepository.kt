package com.tyehooney.domain.repository

import com.tyehooney.domain.model.Capsule
import kotlinx.coroutines.flow.Flow

interface CapsuleRepository {
    suspend fun refreshCapsules()
    fun loadCapsules(): Flow<List<Capsule>?>
    suspend fun addCapsule(capsule: Capsule)
    suspend fun getCapsuleByName(name: String): List<Capsule>?
    suspend fun getStarredCapsules(): List<Capsule>?
    suspend fun getCapsuleById(id: Int): Capsule?
    suspend fun searchCapsulesByName(name: String): List<Capsule>?
    fun searchCapsuleById(id: Int): Flow<Capsule?>
    suspend fun updateCapsuleMajor(capsuleId: Int)
}