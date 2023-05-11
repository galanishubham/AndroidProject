package com.example.astronautsapp.ui.screens.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.astronautsapp.R
import com.example.astronautsapp.prefs
import com.example.astronautsapp.ui.navigation.Screens
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = LoginViewModel()

    when(viewModel.bioMetricAuthUiState) {
        is BioMetricAuthUiState.Success -> navController.navigate(Screens.HomeScreen.route)
        else -> LoginCard(navController = navController, viewModel)
    }

}

@Composable
fun LoginCard(navController: NavHostController, viewModel: LoginViewModel,modifier: Modifier = Modifier) {

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = modifier.height(24.dp))
        Image(painter = painterResource(id = R.drawable.business_3d_astronaut_waving), contentDescription = "", modifier.size(250.dp) .clip(
            CircleShape) .background(Color.LightGray) .padding(12.dp) )
        Spacer(modifier = modifier.height(24.dp))
        Text(text = stringResource(id = R.string.login_text), fontWeight = FontWeight.Bold, fontSize = 28.sp)
        Spacer(modifier = modifier.height(8.dp))
        Text(text = stringResource(id = R.string.login_text_description),  fontSize = 18.sp)
        Spacer(modifier = modifier.height(24.dp))

        val scope = rememberCoroutineScope()

        val token = prefs.accessTokenPref

        if(token != "") {
            BioMetricButton(viewModel, navController)
        } else {
            AndroidView({context -> LoginButton(context)
                .apply {
                    val callbackManager = CallbackManager.Factory.create()

                    setPermissions("email", "public_profile")

                    registerCallback(
                        callbackManager,
                        object : FacebookCallback<LoginResult> {
                            override fun onSuccess(loginResult: LoginResult) {
                                scope.launch {

                                    val accessToken = AccessToken.getCurrentAccessToken()
                                    val isLoggedIn = accessToken != null && !accessToken.isExpired
                                    if(isLoggedIn) {
                                        prefs.accessTokenPref = loginResult.accessToken.token

                                        navController.navigate(Screens.HomeScreen.route)
                                    } else {
                                        Log.d("Login", "login failed")
                                    }
                                }
                            }

                            override fun onCancel() {
                                Log.d("Login", "Cancel login")
                            }

                            override fun onError(exception: FacebookException) {
                                Log.d("Login", exception.message.toString())

                            }
                        },
                    )
                }

            }, modifier.fillMaxWidth() )

        }
    }
}

@Composable
fun BioMetricButton(viewModel: LoginViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    var context = LocalContext.current

    Spacer(modifier = modifier.height(24.dp))

    Button(onClick = { viewModel.launchBiometric(context, navController)}, modifier = modifier.height(100.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), shape = CircleShape) {
        Icon(painter = painterResource(id = R.drawable.baseline_fingerprint_24), contentDescription = "finger print lock", modifier = Modifier.size(70.dp))
    }

    Spacer(modifier = modifier.height(24.dp))

    Text(text = stringResource(id = R.string.login_with_biometric), fontSize = 18.sp)

}
