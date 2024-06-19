package com.adriand.nightthoughts.presentation.quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adriand.nightthoughts.databinding.FragmentQuoteBinding
import com.adriand.nightthoughts.domain.model.Author
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuoteBinding.inflate(layoutInflater)

        quoteViewModel.addAuthorsToList()
        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.rvAuthors.setHasFixedSize(true)
        binding.rvAuthors.layoutManager = LinearLayoutManager(requireContext())

        quoteViewModel.authorList.observe(viewLifecycleOwner) {
            val authorAdapter = AuthorAdapter(it) { author ->  goToDetails(author) }

            binding.rvAuthors.adapter = authorAdapter
        }
    }

    private fun goToDetails(author: Author){
        Toast.makeText(requireContext(), author.name, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}