package com.example.dgtimer.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CapsuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(capsule: Capsule?)

    @Query("SELECT * FROM capsules ORDER BY major desc, name")
    fun getAll(): LiveData<List<Capsule?>?>?

    @Query("SELECT * FROM capsules WHERE name = :name")
    fun getByName(name: String?): List<Capsule?>?

    @Query("SELECT * FROM capsules WHERE id = :id")
    fun getCapsuleById(id: Int): Capsule?

    @Query("SELECT * FROM capsules WHERE name LIKE :name ORDER BY major desc")
    fun searchByName(name: String?): List<Capsule?>?

    @Query("SELECT * FROM capsules WHERE id = :id LIMIT 1")
    fun searchById(id: Int): LiveData<Capsule?>?

    @Query("UPDATE capsules SET major = :major WHERE id = :id")
    fun updateMajor(id: Int, major: Boolean)

    @Update
    fun updateCapsule(capsule: Capsule?)
}