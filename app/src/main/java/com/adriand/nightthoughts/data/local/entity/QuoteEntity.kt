package com.adriand.nightthoughts.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.adriand.nightthoughts.domain.model.Quote

@Entity(
    tableName = "quote_table",
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("idAuthor"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "idAuthor") val idAuthor: Int,
    @ColumnInfo(name = "quote") val quote: String
)

fun Quote.toDatabase() = QuoteEntity(id = id, idAuthor = author, quote = quote)