package com.maxi.news.domain.model

import com.google.gson.annotations.SerializedName

data class CountryRecord(
    @SerializedName("country_codes")
    val countryCodes: List<List<String>>
)
