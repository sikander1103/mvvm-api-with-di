package com.example.apistructure.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apistructure.data.repository.LoginRepository
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.CatogaryModel
import com.example.apistructure.model.Catogery
import com.example.apistructure.model.UserProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatogaryViewmodel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModel() {

    private val _Catogary = MutableLiveData<DataState<CatogaryModel>?>()
    val CatogaryState: MutableLiveData<DataState<CatogaryModel>?> = _Catogary



    fun GetCatogaryData(Page:String,Limit:String,Search:String){
        viewModelScope.launch {
            _Catogary.value = DataState.Loading
            try {
                val response = repository.getCAtogary(Page,Limit, Search)

                if (response.status == true) {

                    _Catogary.value = DataState.Success(response)

                } else {

                    _Catogary.value = DataState.Error("some thing went wrong")

                }
            } catch (e: Exception) {

                _Catogary.value= DataState.Error("Login failed with exception: ${e.message}")

            }
        }
    }



}
