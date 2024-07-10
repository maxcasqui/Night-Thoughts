package com.adriand.nightthoughts.presentation.quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.adriand.nightthoughts.R
import com.adriand.nightthoughts.databinding.FragmentRandomQuoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomQuoteFragment : Fragment() {

    private var _binding: FragmentRandomQuoteBinding? = null
    private val binding get() = _binding!!
    private val randomQuoteViewModel: RandomQuoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomQuoteBinding.inflate(layoutInflater)

        randomQuoteViewModel.onCreate()

        randomQuoteViewModel.quoteModel.observe(viewLifecycleOwner) {
            binding.tvQuote.text = getString(R.string.quote, it.quote)
            binding.tvAuthor.text = it.author
        }

        randomQuoteViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }

        binding.viewContainer.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                randomQuoteViewModel.randomQuote()
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}