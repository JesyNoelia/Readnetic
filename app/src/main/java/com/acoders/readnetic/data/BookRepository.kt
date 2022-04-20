package com.acoders.readnetic.data

import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.network.BookService
import com.acoders.readnetic.data.network.Resource
import com.acoders.readnetic.ui.view.extensions.toBook
import javax.inject.Inject

class BookRepository @Inject constructor(private val service: BookService) {

    suspend fun getBooksByISBN(isbn: String): Resource<List<Book>> {
        val bookList = service.getBooksByISBN(isbn)
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }

    suspend fun getBooksByAnyData(data: String): Resource<List<Book>> {
        val bookList = service.getBooksByAnyData(data)
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }

    suspend fun getBestsellers(): Resource<List<Book>> {
        val bookList = service.getBestsellers()
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }

    //suspend fun updateDB() {}
}
