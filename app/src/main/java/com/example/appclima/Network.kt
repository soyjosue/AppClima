package com.example.appclima

import android.content.Context
import android.net.ConnectivityManager

class Network {

    companion object {
        fun hayRed(activity: MainActivity):Boolean {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected

        }
    }

}