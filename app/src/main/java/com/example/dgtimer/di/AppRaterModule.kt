package com.example.dgtimer.di

import com.example.dgtimer.AppRater
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
interface AppRaterModule {
    @Binds
    fun provideAppRater(appRater: AppRater): AppRater
}