package com.maxi.news.domain.model

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("record")
    val record: CountryRecord
)
