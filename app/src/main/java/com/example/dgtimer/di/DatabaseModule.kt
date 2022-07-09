package com.example.dgtimer.di

import android.content.Context
import androidx.room.Room
import com.example.dgtimer.db.CapsuleDao
import com.example.dgtimer.db.CapsuleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CapsuleDatabase {
        return Room.databaseBuilder(
            context,
            CapsuleDatabase::class.java,
            "capsules"
        ).build()
    }

    @Provides
    fun provideContactDAO(database: CapsuleDatabase): CapsuleDao {
        return database.capsuleDao()
    }
}