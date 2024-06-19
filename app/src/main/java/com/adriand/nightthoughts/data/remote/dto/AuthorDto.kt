package com.adriand.nightthoughts.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    @SerializedName("id_autor") val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("biografia") val biography: String,
    @SerializedName("fecha_nacimiento") val birthDate: String,
    @SerializedName("fecha_fallecimiento") val deathDate: String,
    @SerializedName("url_foto") val photo: String
)
