package com.tyehooney.data.di

import com.tyehooney.domain.repository.CapsuleRepository
import com.tyehooney.data.CapsuleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideContractRepositoryImpl(repository: CapsuleRepositoryImpl): CapsuleRepository
}