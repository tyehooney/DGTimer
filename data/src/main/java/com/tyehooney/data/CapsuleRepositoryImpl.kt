package com.tyehooney.data

import com.tyehooney.domain.repository.CapsuleRepository
import com.tyehooney.domain.model.Capsule
import com.tyehooney.domain.model.GlobalCapsule
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CapsuleRepositoryImpl @Inject constructor(
    private val capsuleDao: CapsuleDao
) : CapsuleRepository {

    private val ioDispatcher = Dispatchers.IO

    override suspend fun refreshCapsules() =
        withContext(ioDispatcher) {
            val collection = FirebaseFirestore.getInstance().collection(FIREBASE_COLLECTION_NAME)
            val newCapsules = collection.get().await().toObjects(GlobalCapsule::class.java).map {
                it.toCapsule().toEntity()
            }
            if (newCapsules.isNotEmpty()) {
                capsuleDao.insertCapsules(newCapsules)
            }
        }

    private fun GlobalCapsule.toCapsule(): Capsule {
        val existingCapsule = capsuleDao.getCapsuleById(id)
        return Capsule(
            id = id,
            name = nameInfo?.localString ?: name,
            typeId = typeId,
            stage = stage,
            color = color,
            image = image,
            isMajor = existingCapsule?.isMajor ?: false
        )
    }

    override fun loadCapsules(): Flow<List<Capsule>?> =
        capsuleDao.getAll().map { entities ->
            entities?.map { it.toDomain() } ?: getSampleCapsules()
        }
    
    private fun getSampleCapsules(): List<Capsule> = listOf(
        Capsule(
            id = 1,
            name = "Espresso",
            typeId = 0,
            stage = listOf(30, 60),
            isMajor = false,
            image = null,
            color = "#8B4513"
        ),
        Capsule(
            id = 2,
            name = "Lungo",
            typeId = 0, 
            stage = listOf(45, 90),
            isMajor = false,
            image = null,
            color = "#D2B48C"
        ),
        Capsule(
            id = 3,
            name = "Americano",
            typeId = 0,
            stage = listOf(30, 120),
            isMajor = true,
            image = null,
            color = "#A0522D"
        )
    )

    override suspend fun addCapsule(capsule: Capsule) {
        capsuleDao.insertCapsule(capsule.toEntity())
    }

    override suspend fun getCapsuleByName(name: String): List<Capsule>? =
        withContext(ioDispatcher) {
            capsuleDao.getByName(name)?.map { it.toDomain() }
        }

    override suspend fun getStarredCapsules(): List<Capsule>? =
        withContext(ioDispatcher) {
            capsuleDao.getStarredCapsules()?.map { it.toDomain() }
        }

    override suspend fun getCapsuleById(id: Int): Capsule? =
        withContext(ioDispatcher) {
            capsuleDao.getCapsuleById(id)?.toDomain() ?: getSampleCapsules().find { it.id == id }
        }

    override suspend fun searchCapsulesByName(
        name: String
    ): List<Capsule>? = withContext(ioDispatcher) {
        if (name.isNotEmpty()) {
            capsuleDao.searchByName(name)?.map { it.toDomain() }
        } else {
            emptyList()
        }
    }

    override fun searchCapsuleById(id: Int): Flow<Capsule?> =
        capsuleDao.searchById(id).map { it?.toDomain() }

    override suspend fun updateCapsuleMajor(capsuleId: Int) =
        withContext(ioDispatcher) {
            val selectedCapsule = capsuleDao.getCapsuleById(capsuleId) ?: return@withContext
            capsuleDao.updateCapsule(selectedCapsule.copy(isMajor = !selectedCapsule.isMajor))
        }

    companion object {
        private const val FIREBASE_COLLECTION_NAME = "capsules"
    }
}