package com.example.astronautsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.astronautsapp.ui.screens.astronaut_details.AstronautDetailsScreen
import com.example.astronautsapp.ui.screens.astronauts.HomeScreen
import com.example.astronautsapp.ui.screens.login.LoginScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route)
    {
        composable(route= Screens.LoginScreen.route) {

            LoginScreen(navController = navController)
        }

        composable(route = Screens.HomeScreen.route){

            HomeScreen(navController)
        }

        composable(route = "${Screens.AstronautDetailsScreen.route}/{astronautId}", arguments = listOf(navArgument("astronautId"){type = NavType.IntType})){

            val astronautId = it.arguments?.getInt("astronautId")

            AstronautDetailsScreen(astronautId)
        }


    }
}
