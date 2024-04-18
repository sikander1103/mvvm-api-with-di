package com.example.apistructure.model


data class CatogaryModel (
    val status: Boolean? = null,
    val data: newdata? = null,
    val message: String? = null,
    val errors: Any? = null
)

data class newdata (
    val results: List<newResult>? = null,
    val count: Long? = null
)

data class newResult (
    val _id: String? = null,
    val CategoryName: String? = null,
    val CategoryLogo: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val __v: Long? = null
)