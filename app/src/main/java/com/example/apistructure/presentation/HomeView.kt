package com.example.apistructure.presentation

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.apistructure.components.common.ShowProgressDialog
import com.example.apistructure.exception.DataState
import com.example.apistructure.viewmodels.LoginViewModel


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val loginViewModel: LoginViewModel = viewModel
    val loginState = loginViewModel.loginState.observeAsState()

    val isLoading = remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {

                    // Call login method from ViewModel with email and password
                    loginViewModel.login(email, password)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }

        // Handle different states of login process
        when (val state = loginState.value) {
            is DataState.Loading -> {
                isLoading.value = true
                loginViewModel.resetLoginState()
            }
            is DataState.Success -> {
                isLoading.value = false
                // Navigate to next screen or perform any action on successful login
                Toast.makeText(LocalContext.current, "${state.data.message}", Toast.LENGTH_SHORT).show()
                loginViewModel.resetLoginState()
            }
            is DataState.Error -> {
                isLoading.value = false
                Toast.makeText(LocalContext.current, "${state.errorMessage}", Toast.LENGTH_SHORT).show()
                loginViewModel.resetLoginState()
            }
else ->{

}
        }
                if (isLoading.value) {
            ShowProgressDialog(isLoading)
        }
    }
}


private val errorToastState = mutableStateOf(false)
private val errorMessageToastState = mutableStateOf("")
private val errorToastInBlueState = mutableStateOf(false)
private val errorMessageToastInBlueState = mutableStateOf("")
fun showToast(message: String, duration: Long = 3000L) {
    if (!errorToastState.value) {
        errorMessageToastState.value = message
        errorToastState.value = true
        errorToastInBlueState.value = false
        val timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                errorToastState.value = false
//                errorMessageToastState.value = ""
            }
        }
        timer.start()
    }
}