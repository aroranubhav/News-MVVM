package com.maxi.news.di.module

import com.maxi.news.data.remote.PreferencesNetworkService
import com.maxi.news.data.repository.LanguagesRepositoryImpl
import com.maxi.news.domain.repository.LanguagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LanguagesModule {

    @Provides
    fun provideLanguagesRepository(
        networkService: PreferencesNetworkService
    ): LanguagesRepository =
        LanguagesRepositoryImpl(networkService)
}