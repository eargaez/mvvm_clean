package com.eargaez.mvvm_clean.data.remote

import com.eargaez.mvvm_clean.domain.models.Beer
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getBeers(
        @Url url: String
    ) : List<Beer>
}