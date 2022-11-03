package com.acoders.readnetic.data


import com.acoders.readnetic.data.datasource.BookLocalDataSource
import com.acoders.readnetic.data.datasource.BookRemoteDataSource
import com.acoders.readnetic.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class BookRepository @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val localDataSource: BookLocalDataSource
) {

    fun getLocalBookByIsbn(isbn: String): Flow<Book> =
        localDataSource.getBookByISBN(isbn).map { it.toBook() }

    suspend fun saveLocalBestsellers(books: List<Book>) =
        localDataSource.saveAllBooks(books.map { it.toBookEntity() })

    fun getLocalBestsellers(): Flow<List<Book>> =
        localDataSource.getAllBooks().map { list ->
            list.map { bookEntity -> bookEntity.toBook() }
        }

    suspend fun updateFavoriteBestSeller(book: Book) =
        localDataSource.updateFavoriteBook(book.toBookEntity())

    suspend fun getRemoteBestsellers(): Flow<List<Book>> =
        remoteDataSource.getBestsellers()
            .map { bookNYTList -> bookNYTList.map { bookNYT -> bookNYT.toBook() } }
}
