package com.example.apistructure.presentation.viewmodels

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apistructure.Session
import com.example.apistructure.data.di.NetworkModule
import com.example.apistructure.data.repository.LoginRepository
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.LoginRequest
import com.example.apistructure.model.LoginResponse
import com.example.apistructure.presentation.Keys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val _session: Session
) : ViewModel() {
    val session get() = _session
    private val _loginState = MutableLiveData<DataState<LoginResponse>?>()
    val loginState: MutableLiveData<DataState<LoginResponse>?> = _loginState
    fun resetLoginState() {
        _loginState.value = null // or any initial state you prefer
    }
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = DataState.Loading
            try {

                val response = repository.login(LoginRequest(email, password))

                if (response.status) {

                    _loginState.value = DataState.Success(response)
                    session.put(Keys.KEY_TOKEN,response.data.token)

                } else {

                    _loginState.value = DataState.Error("Login failed due to incorrect credentials.")

                }
            } catch (e: Exception) {

                _loginState.value = DataState.Error("Login failed with exception: ${e.message}")

            }
        }
    }

}

