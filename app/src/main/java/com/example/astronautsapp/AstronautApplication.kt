package com.example.astronautsapp

import android.app.Application
import com.example.astronautsapp.data.AppContainer
import com.example.astronautsapp.data.DefaultAppContainer
import com.example.astronautsapp.util.SharedPref
import com.facebook.CallbackManager
import com.facebook.FacebookSdk

// global var to access SharedPreference
val prefs: SharedPref by lazy { AstronautApplication.prefs!! }

class AstronautApplication: Application() {

    companion object {
        lateinit var prefs: SharedPref
    }

    lateinit var container: AppContainer


    override fun onCreate() {
        super.onCreate()
        prefs = SharedPref(applicationContext)
        CallbackManager.Factory.create();
        container = DefaultAppContainer(context = applicationContext)
    }
}

