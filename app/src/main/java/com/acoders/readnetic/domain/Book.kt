package com.acoders.readnetic.domain

import com.acoders.readnetic.data.database.entity.BookEntity
import com.acoders.readnetic.data.network.model.BookNyt
import java.io.Serializable

data class Book(
    val isbn: String,
    val authors: String,
    val title: String,
    val description: String,
    val bookPicture: String,
    val date: String,
    val favorite: Boolean = false
): Serializable

fun BookNyt.toBook() =
    Book(
        isbn = primaryIsbn13,
        authors = author,
        title = title,
        description = description,
        bookPicture = bookImage,
        date = createdDate
    )

fun Book.toBookEntity() =
    BookEntity(
        isbn = isbn,
        author = authors,
        title = title,
        description = description,
        imageUrl = bookPicture,
        date = date,
        favorite = favorite
    )

fun BookEntity.toBook() =
    Book(
        isbn = isbn,
        authors = author,
        title = title,
        description = description,
        bookPicture = imageUrl,
        date = date,
        favorite = favorite
    )
