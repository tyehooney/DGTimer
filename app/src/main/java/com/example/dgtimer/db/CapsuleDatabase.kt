package com.example.dgtimer.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Capsule.class}, version = 1)
public abstract class CapsuleDatabase extends RoomDatabase {
    public abstract CapsuleDao getCapsuleDao();
}
