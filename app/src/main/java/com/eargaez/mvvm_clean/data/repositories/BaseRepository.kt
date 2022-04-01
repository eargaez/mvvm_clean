package com.eargaez.mvvm_clean.data.repositories

import com.eargaez.mvvm_clean.domain.Resource
import com.eargaez.mvvm_clean.utils.NoInternetException
import retrofit2.HttpException

/**
 * Created by Eduardo on 4/9/21
 */
abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : Resource<T> {
        return try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException ->
                        Resource
                            .Failure(
                                false,
                                throwable.response()?.code(),
                                throwable.response()?.message()
                            )
                    is NoInternetException ->
                        Resource.Failure(true, null, null)
                    else ->
                        Resource.Failure(false, null, throwable.message)
                }
            }
    }
}