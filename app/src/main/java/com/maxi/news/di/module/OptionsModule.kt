package com.maxi.news.di.module

import com.maxi.news.data.repository.OptionsRepositoryImpl
import com.maxi.news.domain.repository.OptionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class OptionsModule {

    @Provides
    fun provideOptionsRepository(): OptionsRepository =
        OptionsRepositoryImpl()
}