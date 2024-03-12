package com.example.apistructure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apistructure.data.repository.ProductRepository
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.ProductItem
import com.example.apistructure.model.productlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel(){
   private  val _products =MutableLiveData<DataState<productlist>>()
    val products : LiveData<DataState<productlist>> = _products
    fun fetchProduct (){
        viewModelScope.launch {

            try {
                _products.value = DataState.Loading
                val productList = repository.getProducts()
                _products.value = DataState.Success(productList)

            } catch (e:Exception){

                _products.value = DataState.Error(errorMessage = e.message ?: "Unknown error")

            }

        }

    }


}

//                val productList = repository.getProducts()
//                _products.value = DataState.Success(productList)