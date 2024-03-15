package com.example.apistructure.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apistructure.data.repository.LoginRepository
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.LoginRequest
import com.example.apistructure.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val _loginState = MutableLiveData<DataState<LoginResponse>>()
    val loginState: LiveData<DataState<LoginResponse>> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = DataState.Loading
            try {
                val response = repository.login(LoginRequest(email, password))
                if (response.status) {
                    _loginState.value = DataState.Success(response)
                } else {

                    _loginState.value = DataState.Error("Login failed due to incorrect credentials.")
                }
            } catch (e: Exception) {
                _loginState.value = DataState.Error("Login failed with exception: ${e.message}")
            }
        }
    }
}

