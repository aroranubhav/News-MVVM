package com.maxi.news.di.module

import com.maxi.news.data.repository.OptionsRepositoryImpl
import com.maxi.news.domain.repository.OptionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelScoped::class)
class OptionsModule {

    @Provides
    fun provideOptionsRepository(): OptionsRepository =
        OptionsRepositoryImpl()
}