package com.example.dgtimer.di

import com.example.dgtimer.repo.CapsuleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideContractRepositoryImpl(repository: CapsuleRepository): CapsuleRepository
}