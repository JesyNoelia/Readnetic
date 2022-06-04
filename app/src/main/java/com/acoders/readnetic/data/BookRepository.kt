package com.acoders.readnetic.data


import com.acoders.readnetic.data.datasource.BookLocalDataSource
import com.acoders.readnetic.data.network.BookService
import com.acoders.readnetic.data.network.Resource
import com.acoders.readnetic.ui.view.extensions.toBook
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.Book
import javax.inject.Inject


class BookRepository @Inject constructor(
    private val service: BookService,
    private val localDataSource: BookLocalDataSource
    ) {

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

    suspend fun switchFavorite(book: Book): Error? {
        val updatedBook = book.copy(favorite = !book.favorite!!)
        return localDataSource.save(listOf(updatedBook))
    }

    //suspend fun updateDB() {}
}
