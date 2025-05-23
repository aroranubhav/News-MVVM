package com.maxi.news.domain.usecase

import com.maxi.news.domain.model.NewsSource
import com.maxi.news.domain.repository.NewsSourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsSourcesUseCase @Inject constructor(
    private val repository: NewsSourcesRepository
) {

    suspend operator fun invoke(): Flow<List<NewsSource>> =
        repository.getNewsSources()
}