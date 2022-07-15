package com.acoders.readnetic.data


import com.acoders.readnetic.data.database.entity.BookEntity
import com.acoders.readnetic.data.datasource.BookLocalDataSource
import com.acoders.readnetic.data.datasource.BookRemoteDataSource
import com.acoders.readnetic.data.network.Resource
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.toBook
import com.acoders.readnetic.domain.toBookEntity
import com.acoders.readnetic.domain.tryCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val localDataSource: BookLocalDataSource
) {

    /*suspend fun getBooksByISBN(isbn: String): Resource<List<Book>> {
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
    }*/

    suspend fun saveLocalBestsellers(books: List<Book>) {
        return localDataSource.saveAllBooks(books.map { it.toBookEntity() })
    }

    suspend fun getLocalBestsellers(): Flow<Unit> {
        val books = localDataSource.getAllBooks()
        return books.map { it.toBook() }
    }

    /*suspend fun getLocalBestsellers(): List<Book> {
        val books = localDataSource.getAllBooks()
        return books.map { it.toBook() }
    }*/

    suspend fun getRemoteBestsellers(): Resource<List<Book>> {
        val bookList = remoteDataSource.getBestsellers()
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }

    suspend fun switchFavorite(book: BookEntity) =
        tryCall {
            val updatedBook = book.copy(favorite = !book.favorite)
            localDataSource.saveAllBooks(listOf(updatedBook))
        }

    fun getLocalBookByIsbn(isbn: String): Flow<Book> =
        localDataSource.getBookById(isbn).map { it.toBook() }
}
