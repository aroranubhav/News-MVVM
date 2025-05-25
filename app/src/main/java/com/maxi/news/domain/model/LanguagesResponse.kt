package com.maxi.news.domain.model

import com.google.gson.annotations.SerializedName

data class LanguagesResponse(
    @SerializedName("record")
    val record: LanguagesRecord
)
