package com.app.androidmvvmcheezycode.api

import com.app.androidmvvmcheezycode.models.LoginRequest
import com.app.androidmvvmcheezycode.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>
}