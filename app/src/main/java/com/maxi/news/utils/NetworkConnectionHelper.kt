package com.maxi.news.utils

import android.content.Context
import android.net.ConnectivityManager

interface NetworkConnectionHelper {

    fun isConnected(): Boolean
}

class DefaultNetworkConnectionHelper(
    private val context: Context
) : NetworkConnectionHelper {

    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}