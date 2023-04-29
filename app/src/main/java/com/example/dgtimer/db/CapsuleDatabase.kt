package com.example.dgtimer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Capsule::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class CapsuleDatabase : RoomDatabase() {
    abstract fun capsuleDao(): CapsuleDao
}