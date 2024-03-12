package com.example.apistructure.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.apistructure.components.common.ShowProgressDialog

@Composable
fun TestLottie(){
    val isLoading = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
     horizontalAlignment = Alignment.CenterHorizontally,
     verticalArrangement = Arrangement.Center
 ) {
        if (isLoading.value) {
            ShowProgressDialog(isLoading)
        }
        Button(onClick = {  isLoading.value = true }) {
          Text(text = "hello")
        }


 }




}