package com.example.astronautsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.astronautsapp.ui.AstronautInfoApp
import com.example.astronautsapp.ui.theme.AstronautsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AstronautsAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AstronautInfoApp()
                }
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AstronautsAppTheme {
        AstronautInfoApp()
    }
}