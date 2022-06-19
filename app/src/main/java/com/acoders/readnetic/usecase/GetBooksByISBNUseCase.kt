package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.network.Resource
import javax.inject.Inject

class GetBooksByISBNUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke(isbn: String): Resource<List<Book>> {
        val bookList = repository.getBooksByISBN(isbn)
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data)
        }
    }
}