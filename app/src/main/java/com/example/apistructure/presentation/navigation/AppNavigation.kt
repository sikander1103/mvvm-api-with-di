package com.fiel.note.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apistructure.presentation.CatogaryScreen
import com.example.apistructure.presentation.DetailScreen
import com.example.apistructure.presentation.LoginScreen
import com.example.apistructure.presentation.viewmodels.LoginViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController =navController
        , startDestination = Screens.HomeScreen.route
    ){
        composable(route = Screens.HomeScreen.route){
            LoginScreen(navController)
        }
        composable(route = Screens.DetailScreen.route){
            DetailScreen(navController)
        }
        composable(route = Screens.CatogaryScreen.route){
            CatogaryScreen(navController)
        }


//        composable(
//            route = Screens.UpdateScreen.route, arguments = listOf(
//                navArgument("id"){
//                    type= NavType.IntType
//                }
//            )
//        ){
//            UpdateScreen(navController)
//        }




    }

}

sealed class Screens(val route:String){
    data object HomeScreen:Screens("home")
    data object DetailScreen:Screens("DetailScreen")
    data object CatogaryScreen:Screens("CatogaryScreen")
//    data object UpdateScreen:Screens("update/{id}"){
//        fun getById(id:Int)="update/$id"
//    }
}