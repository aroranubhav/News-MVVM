package com.maxi.news.data.repository

import com.maxi.news.data.remote.PreferencesNetworkService
import com.maxi.news.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val networkService: PreferencesNetworkService
) : CountriesRepository {

    override suspend fun getCountries(): Flow<List<List<String>>> {
        return flow {
            emit(networkService.getCountries())
        }.map {
            it.record.countryCodes
        }
    }
}