package com.acoders.readnetic.ui.view.extensions

import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.network.model.BookAPI

fun BookAPI.toBook() = Book(
    authors = volumeInfo?.authors,
    title = volumeInfo?.title,
    description = volumeInfo?.description,
    bookPicture = volumeInfo?.imageLinks?.thumbnail,
    date = volumeInfo?.publishedDate,
    //isbn = volumeInfo?.industryIdentifiers
)