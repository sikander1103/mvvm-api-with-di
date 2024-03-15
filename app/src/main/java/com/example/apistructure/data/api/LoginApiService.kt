package com.example.apistructure.data.api

import com.example.apistructure.model.LoginRequest
import com.example.apistructure.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

        @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}