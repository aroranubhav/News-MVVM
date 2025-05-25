package com.maxi.news.di.module

import com.maxi.news.data.remote.NetworkService
import com.maxi.news.data.repository.HeadlinesRepositoryImpl
import com.maxi.news.domain.repository.HeadlinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HeadlinesModule {

    @Provides
    fun provideHeadlinesRepository(
        networkService: NetworkService
    ): HeadlinesRepository =
        HeadlinesRepositoryImpl(networkService)
}