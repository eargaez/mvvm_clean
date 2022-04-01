package com.eargaez.mvvm_clean.domain.usecases

import com.eargaez.mvvm_clean.data.repositories.BeerRepository
import com.eargaez.mvvm_clean.domain.Resource
import com.eargaez.mvvm_clean.domain.models.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBeersUseCaseImpl @Inject constructor(
    private val repository: BeerRepository
) : GetBeersUseCase {
    override fun invoke(page: Int): Flow<Resource<List<Beer>>> = flow {
        emit(Resource.Loading)

        emit(repository.getBeers(page))
    }
}