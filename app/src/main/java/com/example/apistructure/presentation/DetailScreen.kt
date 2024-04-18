package com.example.apistructure.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var showAllItems by remember { mutableStateOf(true) }
    val detailState = viewModel.DetailState.observeAsState().value
    val userData = remember { mutableStateOf<UserProfileResponse?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val detailStatenew = viewModel.DetailStatenew.observeAsState().value
    val userDatanew = remember { mutableStateOf<Catogery?>(null) }
    val tabTitles = listOf("Zalando", "Skype", "All")
    var selectedTabIndex by remember { mutableStateOf(0) }


    LaunchedEffect(key1 = true) {

  viewModel.userDetailss("1","100","","")

        viewModel.userDetail()

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,

        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }

        val itemsToShow = when (selectedTabIndex) {
            0 -> userDatanew.value?.data?.results?.filter { it.brandName == "Zalando" }
            1 -> userDatanew.value?.data?.results?.filter { it.brandName == "Skype" }
            else -> userDatanew.value?.data?.results
        }
//        itemsToShow?.let { results ->
//            val listState = rememberLazyListState()
//            LazyRow( state = listState) {
//
//                items(results.size) { index ->
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .height(100.dp)
//                            .background(Color.LightGray)
//                    ) {
//                        Row {
//                            Text(text = results[index].brandName)
//
//                            Image(
//                                painter = rememberAsyncImagePainter(results[index].brandLogo),
//                                contentDescription = null,
//                                modifier = Modifier.size(50.dp)
//                            )
//                        }
//                    }
////                    Spacer(modifier = Modifier.height(20.dp))
//                }
//
//
//            }
//
//        }
//        itemsToShow?.let {  results ->
//            Row(modifier = Modifier
//                .padding(horizontal = 20.dp)
//                .horizontalScroll(
//                    rememberScrollState()
//                )) {
//                results.forEachIndexed { index, item ->
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(100.dp)
//                            .background(Color.LightGray)
//                    ) {
//                        Row {
//                            Text(text = item.brandName)
//                            Image(
//                                painter = rememberAsyncImagePainter(item.brandLogo),
//                                contentDescription = null,
//                                modifier = Modifier.size(50.dp)
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.width(30.dp))
//
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(50.dp))
        itemsToShow?.let {  results ->
            Column(modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(
                    rememberScrollState()
                )) {
                results.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.LightGray)
                    ) {
                        Row {
                            Text(text = item.brandName)
                            Image(
                                painter = rememberAsyncImagePainter(item.brandLogo),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(30.dp))

                }
            }
        }
//itemsToShow?.let { results ->
//    val listState = rememberLazyListState()
//    LazyColumn(modifier = Modifier.padding(horizontal = 20.dp), state = listState) {
//
//        items(results.size) { index ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .height(100.dp)
//                    .background(Color.LightGray)
//            ) {
//                Row {
//                    Text(text = results[index].brandName)
//
//                    Image(
//                        painter = rememberAsyncImagePainter(results[index].brandLogo),
//                        contentDescription = null,
//                        modifier = Modifier.size(50.dp)
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//        }
//
//
//    }
//
//}





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
                Toast.makeText(LocalContext.current, "${state.errorMessage}", Toast.LENGTH_SHORT)
                    .show()

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

                }
            }

            is DataState.Error -> {
                isLoading.value = false
                Toast.makeText(LocalContext.current, "${state.errorMessage}", Toast.LENGTH_SHORT)
                    .show()

            }

            else -> {

            }
        }

        if (isLoading.value) {
            ShowProgressDialog(isLoading)
        }
    }


//





//    val userProfile = viewModel.userProfile.observeAsState().value
//            val userProfile = state.data.data
//            Toast.makeText(LocalContext.current, "${userProfile.name}", Toast.LENGTH_SHORT).show()