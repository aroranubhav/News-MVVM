package com.maxi.news.di.module

import com.maxi.news.data.remote.PreferencesNetworkService
import com.maxi.news.data.repository.CountriesRepositoryImpl
import com.maxi.news.domain.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class CountriesModule {

    @Provides
    fun provideCountriesRepository(
        networkService: PreferencesNetworkService
    ): CountriesRepository =
        CountriesRepositoryImpl(networkService)
}