package com.adriand.nightthoughts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adriand.nightthoughts.data.local.dao.QuoteDao
import com.adriand.nightthoughts.data.local.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}