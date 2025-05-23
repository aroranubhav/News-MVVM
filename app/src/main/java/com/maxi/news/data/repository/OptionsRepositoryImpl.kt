package com.maxi.news.data.repository

import com.maxi.news.domain.repository.OptionsRepository
import com.maxi.news.utils.Constants.COUNTRIES
import com.maxi.news.utils.Constants.LANGUAGES
import com.maxi.news.utils.Constants.NEWS_SOURCES
import com.maxi.news.utils.Constants.SEARCH
import com.maxi.news.utils.Constants.TOP_HEADLINES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OptionsRepositoryImpl : OptionsRepository {

    override fun getOptions(): Flow<List<String>> {
        return flow {
            emit(
                listOf(
                    TOP_HEADLINES,
                    NEWS_SOURCES,
                    COUNTRIES,
                    LANGUAGES,
                    SEARCH
                )
            )
        }
    }
}