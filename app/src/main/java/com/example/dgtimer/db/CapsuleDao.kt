package com.example.dgtimer.db;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CapsuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Capsule capsule);

    @Query("SELECT * FROM capsules ORDER BY major desc, name")
    LiveData<List<Capsule>> getAll();

    @Query("SELECT * FROM capsules WHERE name = :name")
    List<Capsule> getByName(String name);

    @Query("SELECT * FROM capsules WHERE id = :id")
    @Nullable
    Capsule getCapsuleById(int id);

    @Query("SELECT * FROM capsules WHERE name LIKE :name ORDER BY major desc")
    List<Capsule> searchByName(String name);

    @Query("SELECT * FROM capsules WHERE id = :id LIMIT 1")
    LiveData<Capsule> searchById(int id);

    @Query("UPDATE capsules SET major = :major WHERE id = :id")
    void updateMajor(int id, boolean major);

    @Update
    void updateCapsule(Capsule capsule);
}
