package com.adriand.nightthoughts.presentation.quote

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.adriand.nightthoughts.R
import com.adriand.nightthoughts.databinding.ItemAuthorBinding
import com.adriand.nightthoughts.domain.model.Author
import com.squareup.picasso.Picasso

class AuthorViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemAuthorBinding.bind(view)

    fun bind(author: Author, onItemSelected: (Author) -> Unit){

        val birthYear = author.birthDate.split("-")[0]
        val deathYear: String = author.deathDate.takeIf { it.isNotBlank() }
            ?.split("-")?.get(0) ?: itemView.context.getString(R.string.author_alive)

        val lifeTime = "$birthYear - $deathYear"

        Picasso.get().load(author.photo).fit().into(binding.imgAuthor)

        binding.tvAuthorName.text = author.name
        binding.tvAuthorBirth.text = lifeTime

        binding.root.setOnClickListener { onItemSelected(author) }
    }
}