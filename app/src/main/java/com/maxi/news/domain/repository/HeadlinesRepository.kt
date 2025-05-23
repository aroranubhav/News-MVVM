package com.maxi.news.domain.repository

import com.maxi.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface HeadlinesRepository {

    suspend fun getHeadlines(): Flow<List<Article>>
}