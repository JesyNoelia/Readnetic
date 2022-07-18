package com.acoders.readnetic.data.datasource

import com.acoders.readnetic.data.database.dao.BooksDao
import com.acoders.readnetic.data.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(private val booksDao: BooksDao) {
    suspend fun saveAllBooks(books: List<BookEntity>) = booksDao.insert(books)

    suspend fun getAllBooks(): List<BookEntity> = booksDao.getAllBooks()

    fun getBookById(isbn: String): Flow<BookEntity> = booksDao.getByIsbn(isbn)

}