package com.acoders.readnetic.data.database.dao

import androidx.room.*
import com.acoders.readnetic.data.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: List<BookEntity>)

    @Query("SELECT * FROM BookEntity")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE isbn = :isbn")
    fun getByIsbn(isbn: String): Flow<BookEntity>

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("SELECT COUNT(isbn) FROM BookEntity")
    suspend fun bookCount() : Int
}