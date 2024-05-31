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
class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotes: GetQuotes

    @RelaxedMockK
    private lateinit var getRandomQuote: GetRandomQuote

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotes, getRandomQuote)
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
            val quoteList = listOf(Quote("quote test 1", "tester"), Quote("quote test 2", "tester"))
            coEvery { getQuotes() } returns quoteList

            // When
            quoteViewModel.onCreate()

            // Then
            assert(quoteViewModel.quoteModel.value == quoteList.first())
        }

    @Test
    fun `when randomQuote returns a quote set on the livedata`() = runTest {

        // Given
        val quote = Quote("quote test", "tester")
        coEvery { getRandomQuote() } returns quote

        // When
        quoteViewModel.randomQuote()

        // Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuote returns null ui should show error message`() = runTest {

        // Given
        val quote = Quote("Error Found", "")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomQuote() } returns null

        // When
        quoteViewModel.randomQuote()

        // Then
        assert(quoteViewModel.quoteModel.value == quote)
    }
}