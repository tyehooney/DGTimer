package com.example.dgtimer.repo

import com.example.dgtimer.db.Capsule
import com.example.dgtimer.db.CapsuleDao
import com.example.dgtimer.db.GlobalCapsule
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CapsuleRepositoryImpl @Inject constructor(
    private val capsuleDao: CapsuleDao
) : CapsuleRepository {

    private val ioDispatcher = Dispatchers.IO

    override fun refreshCapsules(onFinished: (Boolean) -> Unit) {
        val collection = FirebaseFirestore.getInstance().collection(FIREBASE_COLLECTION_NAME)
        collection.get().addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                val newCapsules = task.result.documents.mapNotNull { snapshot ->
                    snapshot.toObject(GlobalCapsule::class.java)
                }.map { globalCapsule ->
                    globalCapsule.toCapsule()
                }
                if (newCapsules.isNotEmpty()) {
                    capsuleDao.insertCapsules(newCapsules)
                }
                onFinished(true)
            } else {
                onFinished(false)
            }
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
        capsuleDao.getAll()

    override suspend fun addCapsule(capsule: Capsule) {
        capsuleDao.insertCapsule(capsule)
    }

    override suspend fun getCapsuleByName(name: String): List<Capsule>? =
        withContext(ioDispatcher) {
            capsuleDao.getByName(name)
        }

    override suspend fun getStarredCapsules(): List<Capsule>? =
        withContext(ioDispatcher) {
            capsuleDao.getStarredCapsules()
        }

    override suspend fun getCapsuleById(id: Int): Capsule? =
        withContext(ioDispatcher) {
            capsuleDao.getCapsuleById(id)
        }

    override suspend fun searchCapsulesByName(
        name: String
    ): List<Capsule>? = withContext(ioDispatcher) {
        if (name.isNotEmpty()) {
            capsuleDao.searchByName(name)
        } else {
            emptyList()
        }
    }

    override fun searchCapsuleById(id: Int): Flow<Capsule?> =
        capsuleDao.searchById(id)

    override suspend fun updateCapsuleMajor(capsuleId: Int) =
        withContext(ioDispatcher) {
            val selectedCapsule = capsuleDao.getCapsuleById(capsuleId) ?: return@withContext
            capsuleDao.updateCapsule(selectedCapsule.copy(isMajor = !selectedCapsule.isMajor))
        }

    companion object {
        private const val FIREBASE_COLLECTION_NAME = "capsules"
    }
}