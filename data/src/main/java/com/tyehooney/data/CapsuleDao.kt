package com.tyehooney.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CapsuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCapsule(capsule: CapsuleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCapsules(capsules: List<CapsuleEntity>)

    @Query("SELECT * FROM capsules ORDER BY major desc, name")
    fun getAll(): Flow<List<CapsuleEntity>?>

    @Query("SELECT * FROM capsules WHERE name = :name")
    fun getByName(name: String): List<CapsuleEntity>?

    @Query("SELECT * FROM capsules WHERE id = :id")
    fun getCapsuleById(id: Int): CapsuleEntity?

    @Query("SELECT * FROM capsules WHERE name LIKE '%' || :name || '%' ORDER BY major desc")
    fun searchByName(name: String): List<CapsuleEntity>?

    @Query("SELECT * FROM capsules WHERE major == 1 ORDER BY name")
    fun getStarredCapsules(): List<CapsuleEntity>?

    @Query("SELECT * FROM capsules WHERE id = :id LIMIT 1")
    fun searchById(id: Int): Flow<CapsuleEntity?>

    @Query("UPDATE capsules SET major = :major WHERE id = :id")
    fun updateMajor(id: Int, major: Boolean)

    @Update
    fun updateCapsule(capsule: CapsuleEntity)
}