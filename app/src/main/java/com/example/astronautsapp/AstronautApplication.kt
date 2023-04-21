package com.example.astronautsapp

import android.app.Application
import com.example.astronautsapp.data.AppContainer
import com.example.astronautsapp.data.DefaultAppContainer

class AstronautApplication: Application() {
    lateinit var container: AppContainer // this var initialized during onCreate fun

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}