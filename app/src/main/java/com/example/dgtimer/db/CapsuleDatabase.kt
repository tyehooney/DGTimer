package com.example.dgtimer.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Capsule::class], version = 1)
abstract class CapsuleDatabase : RoomDatabase() {
    abstract val capsuleDao: CapsuleDao
}