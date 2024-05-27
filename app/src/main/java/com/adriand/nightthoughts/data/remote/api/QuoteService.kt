package com.adriand.nightthoughts.data.remote.api

import com.adriand.nightthoughts.data.remote.dto.QuoteDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor( private val api: QuoteApiClient) {
    suspend fun getQuotes(): List<QuoteDto> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            response.body()?: emptyList()
        }
    }
}