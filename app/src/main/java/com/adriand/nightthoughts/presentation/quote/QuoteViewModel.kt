package com.adriand.nightthoughts.presentation.quote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adriand.nightthoughts.domain.GetAuthors
import com.adriand.nightthoughts.domain.model.Author
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getAuthors: GetAuthors
) : ViewModel() {

    val authorList = MutableLiveData<List<Author>>()

    fun addAuthorsToList(){
        viewModelScope.launch {
            try { authorList.value = getAuthors() }
            catch (exception: Exception) { Log.e("Error", exception.toString()) }
        }
    }
}