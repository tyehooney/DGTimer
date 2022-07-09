package com.example.dgtimer.repo

import com.example.dgtimer.db.Capsule
import com.example.dgtimer.db.CapsuleDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CapsuleRepositoryImpl @Inject constructor(
    private val capsuleDao: CapsuleDao
) : CapsuleRepository {

    override fun refreshCapsules() {
        val collection = FirebaseFirestore.getInstance().collection(FIREBASE_COLLECTION_NAME)
        collection.get().addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                val capsules = task.result.documents.mapNotNull {
                    it.toObject(Capsule::class.java)
                }
                capsuleDao.insertCapsules(capsules)
            }
        }
    }

    override fun loadCapsules(): Flow<List<Capsule>?> =
        capsuleDao.getAll()

    override fun addCapsule(capsule: Capsule) {
        capsuleDao.insertCapsule(capsule)
    }

    override fun getCapsuleByName(name: String): List<Capsule>? =
        capsuleDao.getByName(name)

    override fun getCapsuleById(id: Int): Capsule? =
        capsuleDao.getCapsuleById(id)

    override fun searchCapsulesByName(name: String): List<Capsule>? =
        capsuleDao.searchByName(name)

    override fun searchCapsuleById(id: Int): Flow<Capsule?> =
        capsuleDao.searchById(id)

    override fun updateCapsule(capsule: Capsule) {
        capsuleDao.updateCapsule(capsule)
    }

    companion object {
        private const val FIREBASE_COLLECTION_NAME = "capsules"
    }
}