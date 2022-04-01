package com.eargaez.mvvm_clean.data.remote

import com.eargaez.mvvm_clean.domain.Response
import com.eargaez.mvvm_clean.domain.body.SignIn
import com.eargaez.mvvm_clean.domain.response.Session
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("sign-in")
    suspend fun postSignIn(
        @Body signIn: SignIn
    ) : Response<Session>

    @POST("sign-up")
    suspend fun postSignUp(
        @Body signIn: SignIn
    ) : Response<Session>
}