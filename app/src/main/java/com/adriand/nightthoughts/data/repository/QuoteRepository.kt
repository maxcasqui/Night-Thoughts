package com.adriand.nightthoughts.data.repository

import com.adriand.nightthoughts.data.local.dao.QuoteDao
import com.adriand.nightthoughts.data.local.entity.QuoteEntity
import com.adriand.nightthoughts.data.remote.api.QuoteService
import com.adriand.nightthoughts.domain.model.Quote
import com.adriand.nightthoughts.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        return quoteService.getQuotes().map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote>{
        return quoteDao.getAllQuotes().map { it.toDomain() }
    }

    suspend fun saveQuotes(quotes: List<QuoteEntity>) {
        quoteDao.saveQuotes(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}