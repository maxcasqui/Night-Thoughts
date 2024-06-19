package com.adriand.nightthoughts.data.repository

import com.adriand.nightthoughts.data.local.dao.AuthorDao
import com.adriand.nightthoughts.data.local.entity.AuthorEntity
import com.adriand.nightthoughts.data.remote.api.QuoteService
import com.adriand.nightthoughts.domain.model.Author
import com.adriand.nightthoughts.domain.model.toDomain
import javax.inject.Inject

class AuthorRepository @Inject constructor(
    private val quoteService: QuoteService,
    private val authorDao: AuthorDao
) {
    suspend fun getAllAuthorsFromApi(): List<Author> {
        return quoteService.getAuthors().map { it.toDomain() }
    }

    suspend fun getAllAuthorsFromDatabase(): List<Author>{
        return authorDao.getAllAuthors().map { it.toDomain() }
    }

    suspend fun saveAuthors(authors: List<AuthorEntity>) {
        authorDao.saveAuthors(authors)
    }

    suspend fun clearAuthors() {
        authorDao.deleteAllAuthors()
    }
}