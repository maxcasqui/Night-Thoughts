package com.adriand.nightthoughts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adriand.nightthoughts.data.local.dao.AuthorDao
import com.adriand.nightthoughts.data.local.dao.QuoteDao
import com.adriand.nightthoughts.data.local.entity.AuthorEntity
import com.adriand.nightthoughts.data.local.entity.QuoteEntity

@Database(entities = [QuoteEntity::class, AuthorEntity::class], version = 2)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao

    abstract fun getAuthorDao(): AuthorDao
}