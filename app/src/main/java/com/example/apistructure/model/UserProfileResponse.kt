package com.example.apistructure.model

data class UserProfileResponse(
    val status: Boolean,
    val data: UserProfile,
    val message: String?,
    val errors: String?
)

data class UserProfile(
    val stripeCustomerId: String?,
    val _id: String,
    val name: String,
    val email: String,
    val countryCode: String,
    val isoCode: String,
    val mobileNumber: String,
    val age: Int,
    val profilePic: String?, // Change the type if necessary
    val giftCardPoints: Int,
    val gender: String?, // Change the type if necessary
    val isEmailVerified: Boolean,
    val role: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)
