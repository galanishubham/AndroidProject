package com.example.astronautsapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class InternetConnection {
    companion object {

        // check internet connectivity
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        }
    }
}

