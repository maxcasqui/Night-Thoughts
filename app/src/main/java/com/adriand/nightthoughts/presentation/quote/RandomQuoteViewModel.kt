package com.adriand.nightthoughts.presentation.quote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adriand.nightthoughts.domain.GetQuotes
import com.adriand.nightthoughts.domain.GetRandomQuote
import com.adriand.nightthoughts.domain.model.Quote
import com.adriand.nightthoughts.domain.model.QuoteWithAuthor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel @Inject constructor(
    private val getQuotes: GetQuotes,
    private val getRandomQuote: GetRandomQuote
) : ViewModel() {
    val quoteModel = MutableLiveData<QuoteWithAuthor>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                getQuotes()
                isLoading.postValue(false)
            }
            catch (exception: Exception){
                Log.e("Error", exception.toString())
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val quote = getRandomQuote()

            quote?.let { quoteModel.postValue(it) }
                ?: run { quoteModel.postValue(QuoteWithAuthor("", "")) }

            isLoading.postValue(false)
        }
    }
}