package com.adriand.nightthoughts.domain

import com.adriand.nightthoughts.data.local.entity.toDatabase
import com.adriand.nightthoughts.data.repository.AuthorRepository
import com.adriand.nightthoughts.data.repository.QuoteRepository
import com.adriand.nightthoughts.domain.model.Quote
import com.adriand.nightthoughts.domain.model.QuoteWithAuthor
import javax.inject.Inject

class GetRandomQuote @Inject constructor(
    private val repository: QuoteRepository,
    private val authorRepository: AuthorRepository
) {

    suspend operator fun invoke(): QuoteWithAuthor? {
        val quotes = repository.getAllQuotesFromDatabase()

        if (quotes.isNotEmpty()) {
            val randomNumber = quotes.indices.random()
            val quote = quotes.elementAt(randomNumber).quote
            val author = authorRepository.getAuthorById(quotes.elementAt(randomNumber).author)
            return QuoteWithAuthor(quote = quote, author = author)
        }
        return null
    }
}