package com.adriand.nightthoughts.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adriand.nightthoughts.domain.model.Author

@Entity(tableName = "author_table")
data class AuthorEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url_photo") val photo: String,
    @ColumnInfo(name = "birthDate") val birthDate: String,
    @ColumnInfo(name = "deathDate") val deathDate: String,
    @ColumnInfo(name = "biography") val biography: String,
)

fun Author.toDatabase() = AuthorEntity(id = id, name = name, biography = biography, birthDate = birthDate, deathDate = deathDate, photo = photo)