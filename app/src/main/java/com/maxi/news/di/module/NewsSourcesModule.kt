package com.maxi.news.di.module

import com.maxi.news.data.remote.NetworkService
import com.maxi.news.data.repository.NewsSourcesRepositoryImpl
import com.maxi.news.domain.repository.NewsSourcesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelScoped::class)
class NewsSourcesModule {

    @Provides
    fun provideNewsSourcesRepository(
        networkService: NetworkService
    ): NewsSourcesRepository =
        NewsSourcesRepositoryImpl(networkService)
}