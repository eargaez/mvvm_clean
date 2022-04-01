package com.eargaez.mvvm_clean.di.modules

import com.eargaez.mvvm_clean.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PresentationModule {
    @Binds
    fun bindGetBeersUseCase(getPostsUseCase: GetBeersUseCaseImpl) : GetBeersUseCase
}