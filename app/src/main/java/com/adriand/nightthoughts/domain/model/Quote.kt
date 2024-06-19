package com.adriand.nightthoughts.domain.model

import com.adriand.nightthoughts.data.local.entity.QuoteEntity
import com.adriand.nightthoughts.data.remote.dto.QuoteDto

data class Quote(
    val id: Int,
    val idAuthor: Int,
    val quote: String
)

fun QuoteDto.toDomain() = Quote(id = id, idAuthor = idAuthor, quote = quote)
fun QuoteEntity.toDomain() = Quote(id = id, idAuthor = idAuthor, quote = quote)