package com.example.astronautsapp.ui.navigation

sealed class Screens(val route: String) {
    object LoginScreen: Screens("login_screen")

    object HomeScreen: Screens("home_screen")

    object AstronautDetailsScreen: Screens("astronaut_details_screen/")
}