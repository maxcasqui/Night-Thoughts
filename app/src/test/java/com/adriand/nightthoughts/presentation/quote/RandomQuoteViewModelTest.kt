package com.adriand.nightthoughts.presentation.quote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adriand.nightthoughts.domain.GetQuotes
import com.adriand.nightthoughts.domain.GetRandomQuote
import com.adriand.nightthoughts.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomQuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotes: GetQuotes

    @RelaxedMockK
    private lateinit var getRandomQuote: GetRandomQuote

    private lateinit var randomQuoteViewModel: RandomQuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        randomQuoteViewModel = RandomQuoteViewModel(getQuotes, getRandomQuote)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created for the first time, get all quotes and set the first value`() =
        runTest {

            // Given
            val quoteList = listOf(Quote(0, 0, "quote"), Quote(0, 0, "quote2"))
            coEvery { getQuotes() } returns quoteList

            // When
            randomQuoteViewModel.onCreate()

            // Then
            assert(randomQuoteViewModel.quoteModel.value == quoteList.first())
        }

    @Test
    fun `when randomQuote returns a quote set on the livedata`() = runTest {

        // Given
        val quote = Quote(0, 0, "quote")
        coEvery { getRandomQuote() } returns quote

        // When
        randomQuoteViewModel.randomQuote()

        // Then
        assert(randomQuoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuote returns null ui should show error message`() = runTest {

        // Given
        val quote = Quote(0, 0, "")
        randomQuoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuote() } returns null

        // When
        randomQuoteViewModel.randomQuote()

        // Then
        assert(randomQuoteViewModel.quoteModel.value == quote)
    }
}