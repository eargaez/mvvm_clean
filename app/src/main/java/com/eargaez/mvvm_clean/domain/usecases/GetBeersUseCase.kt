package com.eargaez.mvvm_clean.domain.usecases

import com.eargaez.mvvm_clean.domain.Resource
import com.eargaez.mvvm_clean.domain.models.Beer
import kotlinx.coroutines.flow.Flow

interface GetBeersUseCase {
    operator fun invoke(page: Int): Flow<Resource<List<Beer>>>
}