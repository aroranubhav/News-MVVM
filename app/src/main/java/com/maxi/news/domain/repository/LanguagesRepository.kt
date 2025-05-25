package com.maxi.news.domain.repository

import kotlinx.coroutines.flow.Flow

interface LanguagesRepository {

    suspend fun getLanguages(): Flow<List<List<String>>>
}