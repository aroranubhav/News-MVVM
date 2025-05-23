package com.maxi.news.domain.repository

import com.maxi.news.domain.model.NewsSource
import kotlinx.coroutines.flow.Flow

interface NewsSourcesRepository {

    suspend fun getNewsSources(): Flow<List<NewsSource>>
}