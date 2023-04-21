package com.example.astronautsapp.ui.navigation

sealed class Screens(val route: String) {
    object AstronautDetailsScreen: Screens("astronaut_details_screen/")
    object HomeScreen: Screens("home_screen")
}