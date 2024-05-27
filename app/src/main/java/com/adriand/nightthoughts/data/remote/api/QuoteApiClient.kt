package com.adriand.nightthoughts.data.remote.api

import com.adriand.nightthoughts.data.remote.dto.QuoteDto
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    @GET(".json")
    suspend fun getAllQuotes(): Response<List<QuoteDto>>
}