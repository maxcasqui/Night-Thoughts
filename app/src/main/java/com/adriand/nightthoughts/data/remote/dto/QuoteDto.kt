package com.adriand.nightthoughts.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("id_cita") val id: Int,
    @SerializedName("id_autor") val idAuthor: Int,
    @SerializedName("cita") val quote: String
)