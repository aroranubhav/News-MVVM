package com.maxi.news.domain.usecase

import com.maxi.news.domain.repository.OptionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OptionsUseCase @Inject constructor(
    private val repository: OptionsRepository
) {

    operator fun invoke(): Flow<List<String>> =
        repository.getOptions()
}