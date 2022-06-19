package com.acoders.readnetic.data

import android.util.Log
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.network.BookService
import com.acoders.readnetic.data.network.Resource

import com.acoders.readnetic.ui.view.extensions.toBook
import javax.inject.Inject

class BookRepository @Inject constructor(private val service: BookService) {

    suspend  fun getAllBooksByISBN(isbn: String): Resource<List<Book>> {
        val response = service.getAllBooksByISBN(isbn)
        return when (response.data) {
            null -> Resource.Error(response.message ?: "Something went wrong")
            else -> {
                Log.d("***response", response.data.map { it.toBook()}.toString())
                Resource.Success(response.data.map { it.toBook() })
            }
        }
    }

    suspend  fun getAllBooksByAnyData(anyData: String): Resource<List<Book>> {
        val response = service.getAllBooksByAnyData(anyData)
        return when (response.data) {
            null -> Resource.Error(response.message ?: "Something went wrong")
            else -> {
                Log.d("***response", response.data.map { it.toBook()}.toString())
                Resource.Success(response.data.map { it.toBook() })
            }
        }
    }
}
