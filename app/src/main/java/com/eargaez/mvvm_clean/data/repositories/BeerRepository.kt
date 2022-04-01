package com.eargaez.mvvm_clean.data.repositories

import com.eargaez.mvvm_clean.domain.Resource
import com.eargaez.mvvm_clean.domain.models.Beer

interface BeerRepository {
    suspend fun getBeers(page: Int) : Resource<List<Beer>>
}