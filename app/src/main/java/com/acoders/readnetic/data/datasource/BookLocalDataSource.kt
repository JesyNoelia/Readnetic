package com.acoders.readnetic.data.datasource

import com.acoders.readnetic.data.database.dao.BooksDao
import com.acoders.readnetic.data.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(private val booksDao: BooksDao) {

    suspend fun saveAllBooks(books: List<BookEntity>) = booksDao.insert(books)

    fun getAllBooks(): Flow<List<BookEntity>> = booksDao.getAllBooks()

    fun getBookByISBN(isbn: String): Flow<BookEntity> = booksDao.getByIsbn(isbn)

    suspend fun isEmpty(): Boolean = booksDao.bookCount() ==0

}