package com.maxi.news.domain.usecase

import com.maxi.news.domain.model.Article
import com.maxi.news.domain.repository.HeadlinesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HeadlinesUseCase @Inject constructor(
    private val repository: HeadlinesRepository
) {

    suspend operator fun invoke(): Flow<List<Article>> =
        repository.getHeadlines()
}