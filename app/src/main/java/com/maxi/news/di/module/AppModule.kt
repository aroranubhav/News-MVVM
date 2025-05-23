package com.maxi.news.di.module

import android.content.Context
import com.maxi.news.data.remote.AuthorizationInterceptor
import com.maxi.news.data.remote.NetworkService
import com.maxi.news.di.BaseUrl
import com.maxi.news.utils.DefaultDispatcherProvider
import com.maxi.news.utils.DefaultNetworkConnectionHelper
import com.maxi.news.utils.DispatcherProvider
import com.maxi.news.utils.NetworkConnectionHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String =
        "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor =
        AuthorizationInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        httpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): NetworkService =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider =
        DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideNetworkConnectionHelper(
        @ApplicationContext context: Context
    ): NetworkConnectionHelper =
        DefaultNetworkConnectionHelper(context)
}