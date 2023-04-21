package com.example.astronautsapp.ui.screens.astronaut_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.astronautsapp.domain.model.AstronautDetails
import com.example.astronautsapp.domain.model.Flight
import com.example.astronautsapp.ui.theme.AstronautsAppTheme
import com.example.astronautsapp.R
import com.example.astronautsapp.ui.screens.error.ErrorScreen
import com.example.astronautsapp.ui.screens.loading.LoadingScreen

@Composable
fun AstronautDetailsScreen(astronautDetailsUiState: AstronautDetailsUiState, astronautId: Int? = null, retryAction: (id: Int) -> Unit) {
    when(astronautDetailsUiState) {
        is AstronautDetailsUiState.Loading -> LoadingScreen()
        is AstronautDetailsUiState.Success -> AstronautInfoScreen(astronautDetailsUiState.astronautDetails)
        else -> ErrorScreen({
            if (astronautId != null) {
                retryAction(astronautId)
            }
        })
    }

}

@Composable
fun AstronautInfoScreen(info: AstronautDetails, modifier: Modifier = Modifier ) {
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 16.dp, vertical = 16.dp)) {
        Text(text = info.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        AsyncImage(
            model = info.profileImg,
            contentDescription = null,
            modifier = modifier.clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = stringResource(id = R.string.flights), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        info.flights.forEach{flight -> Text(text = flight.name, modifier.padding(bottom = 8.dp)) }
    }
}


@Preview()
@Composable
fun AstronautInfoScreenPreview() {
    AstronautsAppTheme {
        val flights = List(4) {Flight("$it")}
        val info = AstronautDetails(name = "name", profileImg = "/flight", flights = flights)
        AstronautInfoScreen(info)
    }
}