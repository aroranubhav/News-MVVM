package com.maxi.news.domain.repository

import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    suspend fun getCountries(): Flow<List<List<String>>>
}