package com.adriand.nightthoughts.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)