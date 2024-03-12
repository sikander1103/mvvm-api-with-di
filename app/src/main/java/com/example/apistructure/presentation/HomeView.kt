package com.example.apistructure.presentation

import android.util.Log
import androidx.compose.runtime.Composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.apistructure.components.common.ShowProgressDialog
import com.example.apistructure.exception.DataState
import com.example.apistructure.model.ProductItem
import com.example.apistructure.model.productlist
import com.example.apistructure.viewmodels.ProductViewModel

@Composable
fun ProductListScreen(viewModel: ProductViewModel) {
    val productsViewModel: ProductViewModel = viewModel
    val products = productsViewModel.products.observeAsState()
    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        productsViewModel.fetchProduct()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), contentAlignment = Alignment.Center) {
        products.value?.let {
            when (it) {
                is DataState.Loading -> {
                    isLoading.value = true
                    Log.d("checkstatus","${isLoading.value}")

                }

                is DataState.Error -> {
                    Text(text = "Error: ${it.errorMessage}")
                    isLoading.value = false
                }

                is DataState.Success -> {
                    isLoading.value = false
                    ProductList(it.data)

                }
            }
        }
        if (isLoading.value) {
            ShowProgressDialog(isLoading)
        }
    }

}

@Composable
fun ProductList(products: productlist) {


    LazyColumn(modifier = Modifier.background(Color.White)) {
        items(products) { product ->
            ProductRow(product) {
//                navController.navigate("ProductDetail/${product.id}")
            }
        }
    }
}

@Composable
fun ProductRow(product: ProductItem, onClick: () -> Unit) {
    Column(modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(8.dp))
        .fillMaxWidth()
        .background(Color.Gray)
        .padding(8.dp)
        .clickable(onClick = onClick)
    ) {
        Text(product.category, style = TextStyle(color = Color.Black))
        Text(product.rating.rate.toString(), style = TextStyle(color = Color.Black))
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    }
}