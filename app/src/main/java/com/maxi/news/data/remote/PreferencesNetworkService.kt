package com.maxi.news.data.remote

import com.maxi.news.BuildConfig.COUNTRIES_BIN_ID
import com.maxi.news.BuildConfig.LANGUAGES_BIN_ID
import com.maxi.news.domain.model.CountriesResponse
import com.maxi.news.domain.model.LanguagesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PreferencesNetworkService {

    @GET("{bin_id}")
    suspend fun getCountries(
        @Path("bin_id") binId: String = COUNTRIES_BIN_ID
    ): CountriesResponse

    @GET("{bin_id}")
    suspend fun getLanguages(
        @Path("bin_id") binId: String = LANGUAGES_BIN_ID
    ): LanguagesResponse
}