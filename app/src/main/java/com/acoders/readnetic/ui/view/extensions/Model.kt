package com.acoders.readnetic.ui.view.extensions

import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.network.model.googleBooksModel.BookGoogleBooks
import com.acoders.readnetic.data.network.model.nytmodel.BookNyt

fun BookGoogleBooks.toBook() = Book(
    authors = volumeInfo?.authors,
    title = volumeInfo?.title,
    description = volumeInfo?.description,
    bookPicture = volumeInfo?.imageLinks?.thumbnail,
    date = volumeInfo?.publishedDate,
    //isbn = volumeInfo?.industryIdentifiers
)

fun BookNyt.toBook() = Book(
    authors = listOf(author),
    title = title,
    description = description,
    bookPicture = bookImage,
    date = createdDate,

)