package com.maxi.news.data.repository

import com.maxi.news.data.remote.NetworkService
import com.maxi.news.domain.model.Article
import com.maxi.news.domain.repository.HeadlinesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeadlinesRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : HeadlinesRepository {

    override suspend fun getHeadlines(): Flow<List<Article>> {
        return flow {
            emit(networkService.getHeadlines())
        }.map {
            it.articles
        }
    }
}