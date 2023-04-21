package com.example.astronautsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.astronautsapp.AstronautApplication
import com.example.astronautsapp.ui.screens.astronauts.AstronautsViewModel
import com.example.astronautsapp.ui.screens.astronaut_details.AstronautDetailsScreen
import com.example.astronautsapp.ui.screens.astronaut_details.AstronautDetailsViewModel
import com.example.astronautsapp.ui.screens.astronauts.HomeScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route)
    {
        composable(route = Screens.HomeScreen.route){

            val astronautsViewModel: AstronautsViewModel =
                viewModel(factory = AstronautsViewModel.Factory)
            val retryAction = astronautsViewModel::getAstronauts

            HomeScreen(astronautsListUiState = astronautsViewModel.astronautsListUiState, navController, retryAction)
        }

        composable(route = "${Screens.AstronautDetailsScreen.route}/{astronautId}", arguments = listOf(navArgument("astronautId"){type = NavType.IntType})){

            val astronautId = it.arguments?.getInt("astronautId")

            val astronautDetailsViewModel: AstronautDetailsViewModel =
                viewModel(factory = AstronautDetailsViewModel.Factory)

            val retryAction = astronautDetailsViewModel::getAstronautDetails

            AstronautDetailsScreen(astronautDetailsUiState = astronautDetailsViewModel.astronautDetailsUiState, astronautId, retryAction)
        }


    }
}
