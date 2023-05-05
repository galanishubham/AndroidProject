package com.example.astronautsapp.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context) {
    // pref store name
    private val prefStore = "store"

    // pref key
    private val accessToken = "accessToken"

    private val preferences: SharedPreferences = context.getSharedPreferences(prefStore,Context.MODE_PRIVATE)

    var accessTokenPref: String?
        get() = preferences.getString(accessToken, "")
        set(value) = preferences.edit().putString(accessToken, value).apply()
}
