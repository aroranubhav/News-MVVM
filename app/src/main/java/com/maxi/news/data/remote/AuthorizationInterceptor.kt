package com.maxi.news.data.remote

import com.maxi.news.BuildConfig
import com.maxi.news.utils.Constants.USER_AGENT
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request
                .newBuilder()
                .addHeader("X-Api-Key", BuildConfig.API_KEY)
                .addHeader("User-Agent", USER_AGENT)
                .build()
        )
    }
}