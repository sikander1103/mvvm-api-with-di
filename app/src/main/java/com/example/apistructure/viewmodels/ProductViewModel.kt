package com.example.apistructure.viewmodels

import android.util.Log
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
            try {
                _loginState.value = DataState.Loading
                val response = repository.login(LoginRequest(email, password))
                if (response.status) {
                    // Wrap the entire response inside DataState.Success
                    _loginState.value = DataState.Success(response)
                    _loginState.value = DataState.Error(errorMessage = response.data.user.name ?: "Login failed")
                    Log.d("hello22","helloeaaeadadsad")
                } else {
                    _loginState.value = DataState.Error(errorMessage = response.data ?: "Login failed")
                    Log.d("hello33","helloeaaeadadsad")
                }
            } catch (e: Exception) {
                _loginState.value = DataState.Error(errorMessage = e.message ?: "Unknown error")
                Log.d("hello44","helloeaaeadadsad")
            }
        }
    }
}

