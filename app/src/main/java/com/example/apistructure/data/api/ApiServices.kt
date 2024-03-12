package com.example.apistructure.data.api

import com.example.apistructure.model.productlist
import retrofit2.http.GET

interface ApiServices {
@GET("products")
suspend fun  getProducts(): productlist

}

