package com.example.apistructure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apistructure.presentation.LoginScreen
import com.example.apistructure.ui.theme.ApistructureTheme
import com.example.apistructure.presentation.viewmodels.LoginViewModel
import com.fiel.note.ui.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val productsViewModel: LoginViewModel = viewModel()

            ApistructureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    LoginScreen(productsViewModel)
                    AppNavigation()

                }
            }
        }
    }
}


