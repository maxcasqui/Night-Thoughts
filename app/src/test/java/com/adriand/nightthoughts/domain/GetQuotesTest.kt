package com.adriand.nightthoughts.domain

import com.adriand.nightthoughts.data.repository.QuoteRepository
import com.adriand.nightthoughts.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getQuotes: GetQuotes

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotes = GetQuotes(quoteRepository)
    }

    @Test
    fun `when api returns an empty list get values from database`() = runBlocking {

        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        // When
        getQuotes()

        // Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }

    @Test
    fun `when api returns list with data get values from api`() = runBlocking {

        // Given
        val quoteList = listOf(Quote("quote test", "tester"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns quoteList

        // When
        val response = getQuotes()

        // Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.saveQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(quoteList == response)
    }
}