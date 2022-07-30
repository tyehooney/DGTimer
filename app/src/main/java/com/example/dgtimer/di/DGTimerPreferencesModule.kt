package com.example.dgtimer.di

import com.example.dgtimer.DGTimerPreferences
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
interface DGTimerPreferencesModule {
    @Binds
    fun provideDGTimerPreferences(preferences: DGTimerPreferences): DGTimerPreferences
}