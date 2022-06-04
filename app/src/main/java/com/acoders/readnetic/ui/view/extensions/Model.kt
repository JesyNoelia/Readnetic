package com.acoders.readnetic.ui.view.extensions


import com.acoders.readnetic.data.network.model.googleBooksModel.BookGoogleBooks
import com.acoders.readnetic.data.network.model.nytmodel.BookNyt
import com.acoders.readnetic.domain.Book

fun BookGoogleBooks.toBook() = Book(
    id = 1,
    authors = volumeInfo?.authors,
    title = volumeInfo?.title,
    description = volumeInfo?.description,
    bookPicture = volumeInfo?.imageLinks?.thumbnail?.replace("http://", "https://", true),
    date = volumeInfo?.publishedDate,
    isbn = volumeInfo?.industryIdentifiers,
    favorite = false,

    )

fun BookNyt.toBook() = Book(
    id= 1,
    authors = listOf(author),
    title = title,
    description = description,
    bookPicture = bookImage,
    date = createdDate,
    isbn = null,
    favorite = false,
    )