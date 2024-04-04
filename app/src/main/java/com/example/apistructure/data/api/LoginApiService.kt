package com.example.apistructure.data.api

import com.example.apistructure.model.Catogery
import com.example.apistructure.model.LoginRequest
import com.example.apistructure.model.LoginResponse
import com.example.apistructure.model.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface LoginApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("users/profile")
    suspend fun getUserProfile(): UserProfileResponse
    @GET("brands")
    suspend fun getUserProfilew(
        @Query("page") page: String,
        @Query("limit") limit: String,
        @Query("search") search: String,
        @Query("categoryID") categoryID: String
    ): Catogery

}

//@Header("Authorization") token: String



