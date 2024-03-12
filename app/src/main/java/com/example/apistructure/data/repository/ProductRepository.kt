package com.example.apistructure.data.repository

import com.example.apistructure.data.api.ApiServices
import com.example.apistructure.model.productlist
import javax.inject.Inject

class ProductRepository @Inject constructor (private  val  apiServices: ApiServices){

    suspend fun getProducts():productlist{
        return apiServices.getProducts()
    }

}