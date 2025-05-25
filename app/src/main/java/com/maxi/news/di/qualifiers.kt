package com.maxi.news.di

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class PreferencesBaseUrl

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class BaseHttpClient

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class PreferencesHttpClient
