package com.adriand.nightthoughts.presentation.quote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adriand.nightthoughts.domain.GetQuotes
import com.adriand.nightthoughts.domain.GetRandomQuote
import com.adriand.nightthoughts.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotes: GetQuotes,
    private val getRandomQuote: GetRandomQuote
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quoteList = getQuotes()

            if (quoteList.isNotEmpty()) {
                quoteModel.postValue((quoteList.first()))
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val quote = getRandomQuote()

            quote?.let { quoteModel.postValue(it) }
                ?: run { quoteModel.postValue(Quote("Error Found", "")) }

            isLoading.postValue(false)
        }
    }
}