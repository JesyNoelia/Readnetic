package com.acoders.readnetic.data.database.entity

import androidx.room.*

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "isbn")
    val isbn: Int,
    @ColumnInfo(name = "authors")
    val author: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "date")
    val date: String
)


