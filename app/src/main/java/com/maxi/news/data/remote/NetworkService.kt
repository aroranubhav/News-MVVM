package com.maxi.news.data.remote

import com.maxi.news.domain.model.HeadlineResponse
import com.maxi.news.domain.model.NewsSourcesResponse
import com.maxi.news.utils.Constants.DEFAULT_LANGUAGE
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("sources") source: String = "",
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): HeadlineResponse

    @GET("top-headlines/sources")
    suspend fun getSources(): NewsSourcesResponse
}