package com.adriand.nightthoughts.domain

import com.adriand.nightthoughts.data.repository.QuoteRepository
import com.adriand.nightthoughts.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getRandomQuote: GetRandomQuote

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuote = GetRandomQuote(quoteRepository)
    }

    @Test
    fun `when database is empty return null`() = runBlocking {

        // Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()

        // When
        val response = getRandomQuote()

        // Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty return quote list`() = runBlocking {

        // Given
        val quoteList = listOf(Quote("Testing quote", "tester"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList

        // When
        val response = getRandomQuote()

        // Then
        assert(response == quoteList.first())
    }
}