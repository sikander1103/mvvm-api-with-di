package com.example.apistructure.data.repository

import com.example.apistructure.data.api.LoginApiService
import com.example.apistructure.model.Catogery
import com.example.apistructure.model.LoginRequest
import com.example.apistructure.model.LoginResponse
import com.example.apistructure.model.UserProfileResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApiService: LoginApiService) {

suspend fun login(loginRequest: LoginRequest): LoginResponse {
    return loginApiService.login(loginRequest)
}
    suspend fun getUserProfile(): UserProfileResponse {
        return loginApiService.getUserProfile()
    }

    suspend fun getUserProfilea(page:String,limit:String,Search:String,cid:String): Catogery {
        return loginApiService.getUserProfilew(page,limit, Search,cid)
    }

}




