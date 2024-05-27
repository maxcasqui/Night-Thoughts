package com.adriand.nightthoughts.presentation.quote

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.adriand.nightthoughts.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this) {
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        }

        quoteViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }

        binding.viewContainer.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                quoteViewModel.randomQuote()
            }
        }
    }
}