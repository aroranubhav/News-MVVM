package com.maxi.news.domain.repository

import kotlinx.coroutines.flow.Flow

interface OptionsRepository {

    fun getOptions(): Flow<List<String>>
}