package com.example.astronautsapp.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.astronautsapp.R
import com.example.astronautsapp.prefs
import com.example.astronautsapp.ui.navigation.NavGraph
import com.example.astronautsapp.ui.navigation.Screens
import com.example.astronautsapp.ui.theme.AstronautsAppTheme
import com.facebook.login.LoginManager

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun AstronautAppBar(
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    TopAppBar(
        title= { Text(text = stringResource(id = R.string.app_name))},
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }
        },
        actions = {
            val isLoggedIn = prefs.accessTokenPref
            val isLoginScreen = Screens.LoginScreen.route == navController.currentBackStackEntry?.destination?.route

            if(isLoggedIn != "" && !isLoginScreen) {
                IconButton(onClick = {
                    LoginManager.getInstance().logOut()
                    prefs.accessTokenPref = ""
                    navController.popBackStack(route = Screens.LoginScreen.route, inclusive = false)
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_logout_24), contentDescription = "${ R.string.logout_button }" )
                }
            }
        }

    )
}

@Composable
fun AstronautInfoApp() {
    val navController: NavHostController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    Log.d("BackArrow", backStackEntry?.destination?.route.toString())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AstronautAppBar(
                canNavigateBack = navController.previousBackStackEntry?.destination?.route == Screens.HomeScreen.route,
                navigateUp = { navController.navigateUp() } ,
                navController = navController
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            // handle app navigation
            NavGraph(navController = navController)
        }
    }
}

@Preview
@Composable
fun AstronautInfoAppPreview() {
    AstronautsAppTheme() {
        AstronautInfoApp()
    }
}