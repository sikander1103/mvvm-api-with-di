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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apistructure.components.common.ShowProgressDialog
import com.example.apistructure.exception.DataState
import com.example.apistructure.presentation.viewmodels.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    val loginState by viewModel.loginState.observeAsState()

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
                    viewModel.login(email, password)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }

loginState?.let {
    when(it){
       is DataState .Loading -> {
           isLoading.value = true
           viewModel.resetLoginState()


       }
        is DataState.Success -> {
            isLoading.value = false
            Toast.makeText(LocalContext.current, "${it.data.message}", Toast.LENGTH_SHORT).show()
            viewModel.resetLoginState()

        }
        is DataState.Error -> {
            isLoading.value = false
            Toast.makeText(LocalContext.current, "${it.errorMessage}", Toast.LENGTH_SHORT).show()
            viewModel.resetLoginState()

        }
    }

}

                if (isLoading.value) {
            ShowProgressDialog(isLoading)
        }
    }
}


