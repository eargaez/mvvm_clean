package com.eargaez.mvvm_clean.data.repositories

import com.eargaez.mvvm_clean.data.remote.ApiService
import com.eargaez.mvvm_clean.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor (
    private val api: ApiService,
    private val dispatcher: DispatcherProvider
) : BaseRepository(), BeerRepository {
    override suspend fun getBeers(page: Int) = withContext(dispatcher.io) {
        return@withContext safeApiCall { api.getBeers("https://api.punkapi.com/v2/beers?page=$page") }
    }
}