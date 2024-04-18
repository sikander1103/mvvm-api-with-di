package com.example.apistructure.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apistructure.components.common.ShowProgressDialog
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.CatogaryModel


@Composable
fun CatogaryScreen(navController: NavHostController, viewModel: CatogaryViewmodel = hiltViewModel()) {

    val isLoading = remember { mutableStateOf(false) }
    val CatogaryState = viewModel.CatogaryState.observeAsState().value
    val CatogaryData = remember { mutableStateOf<CatogaryModel?>(null) }
    val tabTitles = listOf("Shoes", "Entertainment", "All")
    var selectedTabIndex by remember { mutableStateOf(0) }


    LaunchedEffect(key1 = true) {

viewModel.GetCatogaryData("1","20","")


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
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.background(Color.Blue)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }

        val itemsToShow = when (selectedTabIndex) {
            0 -> CatogaryData.value?.data?.results?.filter { it.CategoryName == "Shoes" }
            1 -> CatogaryData.value?.data?.results?.filter { it.CategoryName == "Entertainment" }
            else -> CatogaryData.value?.data?.results
        }


        itemsToShow?.let {  results ->
            Row(modifier = Modifier
//                .padding(horizontal = 20.dp)
                .horizontalScroll(
                    rememberScrollState()
                )) {
                results.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.Transparent)
                         ,
                        contentAlignment = Alignment.Center
                    ) {
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//                            Image(
//                                painter = rememberAsyncImagePainter("https://giftcard.builtinsoft.site/${item.CategoryLogo}"),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(56.dp)
//                                    .clickable { }
//                            )

                            LoadImage(path = "https://giftcard.builtinsoft.site/${item.CategoryLogo}")
                            {

                            }
                            Text(text ="${item.CategoryName}", style = TextStyle(fontSize = 10.sp))

                        }
                    }
                    Spacer(modifier = Modifier.width(30.dp))

                }
            }
        }






    }

    when (val state = CatogaryState) {
        is DataState.Loading -> {
            isLoading.value = true
        }

        is DataState.Success -> {
            isLoading.value = false
            val userAccounts = state.data as CatogaryModel
            userAccounts?.let { user ->
                CatogaryData.value = user
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(path: String,onClick:()-> Unit){
    GlideImage(model = path, contentDescription = "Network Image",

        modifier = Modifier
            .size(56.dp)

            .clickable {
                onClick()
            }

    )


}


