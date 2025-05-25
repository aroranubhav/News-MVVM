package com.maxi.news.domain.model

import com.google.gson.annotations.SerializedName

data class LanguagesRecord(
    @SerializedName("language_codes")
    val languageCodes: List<List<String>>
)
