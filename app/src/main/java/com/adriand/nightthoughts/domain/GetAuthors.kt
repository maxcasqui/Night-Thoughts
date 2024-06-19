package com.adriand.nightthoughts.domain

import com.adriand.nightthoughts.data.local.entity.toDatabase
import com.adriand.nightthoughts.data.repository.AuthorRepository
import com.adriand.nightthoughts.domain.model.Author
import javax.inject.Inject

class GetAuthors @Inject constructor(
    private val authorRepo: AuthorRepository
) {
    suspend operator fun invoke(): List<Author>{
        val authors = authorRepo.getAllAuthorsFromApi()

        return if (authors.isNotEmpty()) {
            authorRepo.saveAuthors(authors = authors.map { it.toDatabase() })
            authors
        } else { authorRepo.getAllAuthorsFromDatabase() }
    }
}