package com.adriand.nightthoughts.presentation.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.adriand.nightthoughts.R
import com.adriand.nightthoughts.domain.model.Author

class AuthorAdapter(
    private val authorList: List<Author> = emptyList(),
    private val onItemSelected: (Author) -> Unit
) : Adapter<AuthorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return AuthorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_author, parent, false)
        )
    }

    override fun getItemCount(): Int = authorList.size

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = authorList.elementAt(position)
        holder.bind(author, onItemSelected)
    }

}