package com.maxi.news.domain.model

import com.google.gson.annotations.SerializedName

data class ArticleSource(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String = ""
)
