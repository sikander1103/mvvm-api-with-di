package com.example.apistructure.data.repository

import com.example.apistructure.data.api.LoginApiService
import com.example.apistructure.model.LoginRequest
import com.example.apistructure.model.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApiService: LoginApiService) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return loginApiService.login(loginRequest)
    }
}