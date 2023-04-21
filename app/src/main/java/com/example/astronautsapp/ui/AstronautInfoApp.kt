package com.example.astronautsapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.astronautsapp.R
import com.example.astronautsapp.ui.navigation.NavGraph

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun AstronautAppBar(
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
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
        }
    )
}

@Composable
fun AstronautInfoApp(navController: NavHostController = rememberNavController()) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AstronautAppBar(
                canNavigateBack = navController.previousBackStackEntry?.destination?.route != null,
                navigateUp = { navController.navigateUp() }
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