package com.adriand.nightthoughts.domain

import com.adriand.nightthoughts.data.local.entity.toDatabase
import com.adriand.nightthoughts.data.repository.QuoteRepository
import com.adriand.nightthoughts.domain.model.Quote
import javax.inject.Inject

class GetRandomQuote @Inject constructor( private val repository: QuoteRepository) {

    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase()

        if (quotes.isNotEmpty()) {
            val randomNumber = quotes.indices.random()
            return quotes.elementAt(randomNumber)
        }
        return null
    }
}