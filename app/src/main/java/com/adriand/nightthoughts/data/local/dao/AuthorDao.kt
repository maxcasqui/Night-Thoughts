package com.adriand.nightthoughts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adriand.nightthoughts.data.local.entity.AuthorEntity

@Dao
interface AuthorDao {
    @Query("SELECT * FROM author_table")
    suspend fun getAllAuthors(): List<AuthorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAuthors(authors: List<AuthorEntity>)

    @Query("DELETE FROM author_table")
    suspend fun deleteAllAuthors()
}