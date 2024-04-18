package com.example.apistructure.presentation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apistructure.data.repository.LoginRepository
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.Catogery
import com.example.apistructure.model.UserProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewmodel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModel() {

    private val _Detail = MutableLiveData<DataState<UserProfileResponse>?>()
    val DetailState: MutableLiveData<DataState<UserProfileResponse>?> = _Detail
    private val _Detailnew = MutableLiveData<DataState<Catogery>?>()
    val DetailStatenew: MutableLiveData<DataState<Catogery>?> = _Detailnew

    fun userDetail(){
        viewModelScope.launch {
            _Detail.value = DataState.Loading
            try {
                val response = repository.getUserProfile()

                if (response.status) {

                    _Detail.value = DataState.Success(response)

                } else {

                    _Detail.value = DataState.Error("some thing went wrong")

                }
            } catch (e: Exception) {

                _Detail.value = DataState.Error("Login failed with exception: ${e.message}")

            }
        }
    }

    fun userDetailss(Page:String,Limit:String,Search:String,cid :String){
        viewModelScope.launch {
            _Detailnew.value = DataState.Loading
            try {
                val response = repository.getUserProfilea(Page,Limit,Search,cid)

                if (response.status) {

                    _Detailnew.value = DataState.Success(response)

                } else {

                    _Detailnew.value = DataState.Error("some thing went wrong")

                }
            } catch (e: Exception) {

                _Detailnew.value= DataState.Error("Login failed with exception: ${e.message}")

            }
        }
    }



    }

















