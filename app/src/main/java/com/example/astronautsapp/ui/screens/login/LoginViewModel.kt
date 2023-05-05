package com.example.astronautsapp.ui.screens.login

import android.content.Context
import android.hardware.biometrics.BiometricPrompt
import android.os.CancellationSignal
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.astronautsapp.ui.navigation.Screens
import java.util.concurrent.Executor


sealed interface BioMetricAuthUiState {
    object Success: BioMetricAuthUiState
    object Error: BioMetricAuthUiState
    object Initial: BioMetricAuthUiState

}

class LoginViewModel : ViewModel() {
    var bioMetricAuthUiState: BioMetricAuthUiState by mutableStateOf(BioMetricAuthUiState.Initial)
        private set

    private var cancellationSignal: CancellationSignal? = null
    private var executor: Executor? = null

    fun launchBiometric(context: Context, navController: NavController){
        executor = ContextCompat.getMainExecutor(context)

        val  biometricPrompt = BiometricPrompt
            .Builder(context)
            .setTitle("Allow Biometric Authentication")
            .setSubtitle("You will no longer required username and password during login")
            .setDescription("We use biometric authentication to protect your data")
            .setNegativeButton("Not Now", context.mainExecutor, {
                    dialogInterface,i ->
                notifyUser("Authentication cancelled")

            })
            .build()


        biometricPrompt.authenticate(getCancellationSignal(), executor!!, getAuthenticationCallBack(navController))
    }

    private fun getAuthenticationCallBack(navController: NavController): BiometricPrompt.AuthenticationCallback {
        return object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                notifyUser("Authentication Error $errorCode")
                bioMetricAuthUiState = BioMetricAuthUiState.Error
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
            }

            override fun onAuthenticationFailed() {
                notifyUser("Authentication Failed")
                bioMetricAuthUiState = BioMetricAuthUiState.Error
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                notifyUser("Authentication Succeeded")
                bioMetricAuthUiState = BioMetricAuthUiState.Success
                navController.navigate(Screens.HomeScreen.route)
                super.onAuthenticationSucceeded(result)

            }
        }
    }


    private fun getCancellationSignal(): CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            bioMetricAuthUiState = BioMetricAuthUiState.Error
            notifyUser("Auth Cancelled via Signal")
        }

        return cancellationSignal as CancellationSignal
    }

    private fun notifyUser(message: String){
        Log.d("BIOMETRIC", message)
    }

}