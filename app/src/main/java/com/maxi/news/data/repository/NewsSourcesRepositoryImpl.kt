package com.maxi.news.data.repository

import com.maxi.news.data.remote.NetworkService
import com.maxi.news.domain.model.NewsSource
import com.maxi.news.domain.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourcesRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NewsSourcesRepository {

    override suspend fun getNewsSources(): Flow<List<NewsSource>> {
        return flow {
            emit(networkService.getSources())
        }.map {
            it.sources
        }
    }
}