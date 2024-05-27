package com.adriand.nightthoughts.domain.model

import com.adriand.nightthoughts.data.local.entity.QuoteEntity
import com.adriand.nightthoughts.data.remote.dto.QuoteDto

data class Quote(val quote: String, val author: String)

fun QuoteDto.toDomain() = Quote(quote = quote, author = author)
fun QuoteEntity.toDomain() = Quote(quote = quote, author = author)