package com.example.astronautsapp.ui.screens.astronaut_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.astronautsapp.R
import com.example.astronautsapp.domain.model.AstronautDetails
import com.example.astronautsapp.domain.model.Flight
import com.example.astronautsapp.ui.screens.error.ErrorScreen
import com.example.astronautsapp.ui.screens.loading.LoadingScreen
import com.example.astronautsapp.ui.theme.AstronautsAppTheme


@Composable
fun AstronautDetailsScreen(
    astronautId: Int? = null,
) {
    val astronautDetailsViewModel: AstronautDetailsViewModel =
        viewModel(factory = AstronautDetailsViewModel.Factory)

    val astronautDetailsUiState = astronautDetailsViewModel.astronautDetailsUiState

    val retryAction = astronautDetailsViewModel::getAstronautDetails

    when(astronautDetailsUiState) {
        is AstronautDetailsUiState.Loading -> LoadingScreen()
        is AstronautDetailsUiState.Success -> AstronautInfoScreen(astronautDetailsUiState.astronautDetails, viewModel=astronautDetailsViewModel)
        else -> ErrorScreen({
            if (astronautId != null) {
                retryAction(astronautId)
            }
        })
    }

}

@Composable
fun AstronautInfoScreen(info: AstronautDetails, viewModel: AstronautDetailsViewModel,modifier: Modifier = Modifier ) {
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 16.dp, vertical = 16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            val context = LocalContext.current

            Text(text = info.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = { viewModel.shareWithFacebook(info,context)}) {
                Icon(painter = painterResource(id = R.drawable.baseline_ios_share_24), contentDescription = "sharing button")
            }

        }
        Spacer(modifier = Modifier.height(24.dp))

        AsyncImage(
            model = info.profileImg,
            contentDescription = null,
            modifier = modifier.clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = stringResource(id = R.string.flights), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        info.flights.forEach{flight -> Text(text = flight.flightName, modifier.padding(bottom = 8.dp)) }
    }
}

@Preview()
@Composable
fun AstronautInfoScreenPreview() {
    AstronautsAppTheme {
        val flights = List(4) {Flight("$it", "$it")}
        val info = AstronautDetails(name = "name", profileImg = "/flight", flights = flights, id = 1)
        val astronautDetailsViewModel: AstronautDetailsViewModel =
            viewModel(factory = AstronautDetailsViewModel.Factory)

        AstronautInfoScreen(info, astronautDetailsViewModel)
    }
}