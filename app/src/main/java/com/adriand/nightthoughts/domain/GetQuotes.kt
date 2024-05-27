package com.adriand.nightthoughts.domain

import com.adriand.nightthoughts.data.local.entity.toDatabase
import com.adriand.nightthoughts.data.repository.QuoteRepository
import com.adriand.nightthoughts.domain.model.Quote
import javax.inject.Inject

class GetQuotes @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.saveQuotes(quotes = quotes.map { it.toDatabase() })
            quotes
        } else { repository.getAllQuotesFromDatabase() }
    }
}