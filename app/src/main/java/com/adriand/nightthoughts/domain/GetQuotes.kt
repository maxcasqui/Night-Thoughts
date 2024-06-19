package com.adriand.nightthoughts.domain

import android.util.Log
import com.adriand.nightthoughts.data.local.entity.toDatabase
import com.adriand.nightthoughts.data.repository.AuthorRepository
import com.adriand.nightthoughts.data.repository.QuoteRepository
import com.adriand.nightthoughts.domain.model.Quote
import javax.inject.Inject

class GetQuotes @Inject constructor(
    private val quoteRepo: QuoteRepository
) {
    suspend operator fun invoke(): List<Quote> {
        val quotes = quoteRepo.getAllQuotesFromApi()
        Log.i("Info", quotes.toString())

        return if (quotes.isNotEmpty()) {
            //quoteRepo.clearQuotes()
            quoteRepo.saveQuotes(quotes = quotes.map { it.toDatabase() })
            quotes
        } else {
            Log.i("Info", "Getting data from db")
            quoteRepo.getAllQuotesFromDatabase()
        }
    }
}