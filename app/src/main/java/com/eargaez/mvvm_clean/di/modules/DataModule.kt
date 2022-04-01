package com.eargaez.mvvm_clean.di.modules

import com.eargaez.mvvm_clean.data.repositories.BeerRepository
import com.eargaez.mvvm_clean.data.repositories.BeerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindBeersRepository(postRepository: BeerRepositoryImpl) : BeerRepository
}