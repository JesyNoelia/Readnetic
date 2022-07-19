package com.acoders.readnetic.domain

import com.acoders.readnetic.data.database.entity.BookEntity
import com.acoders.readnetic.data.network.model.nytmodel.BookNyt

data class Book(
    val isbn: String,
    val authors: String,
    val title: String,
    val description: String,
    val bookPicture: String,
    val date: String,
    val favorite: Boolean = false
    //val pages: String,
    //val gender: String,
    //val profileAuthorPicture: String?
)

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

/*
fun List<BookEntity>.toBook(): List<Book> {
    return map {
        Book(
            isbn = it.isbn,
            authors = it.author,
            title = it.title,
            description = it.description,
            bookPicture = it.imageUrl,
            date = it.date,
            favorite = it.favorite
        )
    }
}*/
