package com.example.apistructure.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.apistructure.components.common.ShowProgressDialog
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.Catogery
import com.example.apistructure.model.Resultn
import com.example.apistructure.model.UserProfileResponse

@Composable
fun DetailScreen(navController: NavHostController,viewModel: DetailScreenViewmodel = hiltViewModel()) {

    val detailState = viewModel.DetailState.observeAsState().value
    val userData = remember { mutableStateOf<UserProfileResponse?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val detailStatenew = viewModel.DetailStatenew.observeAsState().value
    val userDatanew = remember { mutableStateOf<Catogery?>(null) }


    LaunchedEffect(key1 = true) {
  viewModel.userDetailss("2","100","","")
        viewModel.userDetail()

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

        if(userData.value!= null){


            Text(text = "${userData.value!!.data.email}")

            Text(text = "${userData.value!!.data.name}")

        }
        userDatanew.value?.data?.results?.let { results ->

            LazyColumn {
                items(results.size) { index ->
                  Box(modifier = Modifier
                      .fillMaxSize()
                      .height(100.dp)
                      .background(Color.LightGray)) {
                   Row {
                       Text(text = results[index].brandName)
                       Image(
                           painter = rememberAsyncImagePainter(results[index].brandLogo),
                           contentDescription = null,
                           modifier = Modifier.size(50.dp)
                       )
                   }
                  }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

    when (val state = detailState) {
        is DataState.Loading -> {
            isLoading.value = true
        }
        is DataState.Success -> {
            isLoading.value = false
            val userAccounts = state.data as UserProfileResponse
            userAccounts?.let { user ->
                userData.value = user
            }
                        }
        is DataState.Error -> {
            isLoading.value = false
            Toast.makeText(LocalContext.current, "${state.errorMessage}", Toast.LENGTH_SHORT).show()

        }
        else -> {

        }
    }
        when (val state = detailStatenew) {
            is DataState.Loading -> {
                isLoading.value = true
            }
            is DataState.Success -> {
                isLoading.value = false
                val userAccounts = state.data as Catogery
                userAccounts?.let { user ->
                    userDatanew.value = user
//                    list =userDatanew.value.data.results
                }
            }
            is DataState.Error -> {
                isLoading.value = false
                Toast.makeText(LocalContext.current, "${state.errorMessage}", Toast.LENGTH_SHORT).show()

            }
            else -> {

            }
        }

    if (isLoading.value) {
        ShowProgressDialog(isLoading)
    }
    }









//    val userProfile = viewModel.userProfile.observeAsState().value
//            val userProfile = state.data.data
//            Toast.makeText(LocalContext.current, "${userProfile.name}", Toast.LENGTH_SHORT).show()