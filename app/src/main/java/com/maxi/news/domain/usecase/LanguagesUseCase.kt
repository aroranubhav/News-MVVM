package com.maxi.news.domain.usecase

import com.maxi.news.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguagesUseCase @Inject constructor(
    private val repository: LanguagesRepository
) {

    suspend operator fun invoke(): Flow<List<List<String>>> =
        repository.getLanguages()
}