package com.adriand.nightthoughts.domain.model

import com.adriand.nightthoughts.data.local.entity.AuthorEntity
import com.adriand.nightthoughts.data.remote.dto.AuthorDto

data class Author(
    val id: Int,
    val name: String,
    val biography: String,
    val birthDate: String,
    val deathDate: String,
    val photo: String
)

fun AuthorDto.toDomain() = Author(id = id, name = name, biography = biography, birthDate = birthDate, deathDate = deathDate, photo = photo)
fun AuthorEntity.toDomain() = Author(id = id, name = name, biography = biography, birthDate = birthDate, deathDate = deathDate, photo = photo)
