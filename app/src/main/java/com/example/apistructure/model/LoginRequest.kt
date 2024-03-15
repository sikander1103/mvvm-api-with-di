package com.example.apistructure.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null
)

data class LoginResponse(
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") val data: LoginData,
    @SerializedName("message") val message: String,
    @SerializedName("errors") val errors: Any? // Adjust based on your actual error structure
)

data class LoginData(
    @SerializedName("token") val token: String,
    // Include other fields as necessary
    @SerializedName("user") val user: LoginUser
)

data class LoginUser(
    // Define fields according to the user information you need
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String
    // Add other user fields as required
)