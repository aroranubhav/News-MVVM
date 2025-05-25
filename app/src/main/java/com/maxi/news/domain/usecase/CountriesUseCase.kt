package com.maxi.news.domain.usecase

import com.maxi.news.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesUseCase @Inject constructor(
    private val repository: CountriesRepository
) {

    suspend operator fun invoke(): Flow<List<List<String>>> =
        repository.getCountries()
}