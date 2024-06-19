package com.adriand.nightthoughts.presentation.quote

import android.util.Log
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
class RandomQuoteViewModel @Inject constructor(
    private val getQuotes: GetQuotes,
    private val getRandomQuote: GetRandomQuote
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            // listOf(Quote(0,0,"example"))
            var quoteList = listOf(Quote(0,0,"example"))
            try { quoteList = getQuotes() }
            catch (exception: Exception){ Log.e("Error", exception.toString()) }

            if (quoteList.isNotEmpty()) {
                quoteModel.postValue((quoteList.last()))
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val quote = getRandomQuote()

            quote?.let { quoteModel.postValue(it) }
                ?: run { quoteModel.postValue(Quote(0, 0, "")) }

            isLoading.postValue(false)
        }
    }
}