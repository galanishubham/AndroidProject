package com.example.astronautsapp.ui.screens.astronauts

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.astronautsapp.R
import com.example.astronautsapp.domain.model.Astronaut
import com.example.astronautsapp.ui.navigation.Screens
import com.example.astronautsapp.ui.screens.error.ErrorScreen
import com.example.astronautsapp.ui.screens.loading.LoadingScreen
import com.example.astronautsapp.ui.theme.AstronautsAppTheme

@Composable
fun HomeScreen(astronautsListUiState: AstronautsListUiState, navController: NavController, retryAction: () -> Unit) {
    when(astronautsListUiState) {
        is AstronautsListUiState.Loading -> LoadingScreen()
        is AstronautsListUiState.Success -> AstronautsListScreen(astronautsListUiState.astronauts, navController)
        else -> ErrorScreen(retryAction)
    }
}


@Composable
fun AstronautsListScreen(astronauts: List<Astronaut>, navController: NavController, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        GridCells.Fixed(1),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
            items(items = astronauts, key = { astronaut: Astronaut -> astronaut.id }) { astronaut ->
                AstronautCard(astronaut, navController)
            }
    }
}

@Composable
fun AstronautCard(astronaut: Astronaut, navController: NavController, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)
        .clickable {
            navController.navigate(
                "${Screens.AstronautDetailsScreen.route}/${astronaut.id}" )}
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = astronaut.profileImg,
                        contentDescription = null,
                        modifier = modifier.clip(
                            CircleShape
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Name: ${astronaut.name}")
                        Text(text = "Age: ${astronaut.age}")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_forward_24),
                        contentDescription = "go to details"
                    )
                }
            }
        }


@Preview(showBackground = true)
@Composable
fun AstronautListPreview() {
    AstronautsAppTheme {
         val astronaut = List(10) { Astronaut("$it".toInt(), 23, "Name", "ImgUrl") }
        val navController = rememberNavController()

        AstronautsListScreen(astronaut, navController)
    }
}

