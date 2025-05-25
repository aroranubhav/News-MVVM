package com.maxi.news.data.repository

import com.maxi.news.data.remote.PreferencesNetworkService
import com.maxi.news.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LanguagesRepositoryImpl @Inject constructor(
    private val networkService: PreferencesNetworkService
) : LanguagesRepository {

    override suspend fun getLanguages(): Flow<List<List<String>>> {
        return flow {
            emit(networkService.getLanguages())
        }.map {
            it.record.languageCodes
        }
    }
}