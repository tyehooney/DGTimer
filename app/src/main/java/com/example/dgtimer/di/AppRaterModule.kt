package com.example.dgtimer.di

import com.example.dgtimer.KAppRater
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
interface AppRaterModule {
    @Binds
    fun provideAppRater(appRater: KAppRater): KAppRater
}