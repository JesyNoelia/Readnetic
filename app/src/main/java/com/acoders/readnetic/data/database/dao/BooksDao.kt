package com.acoders.readnetic.data.database.dao

import androidx.room.*
import com.acoders.readnetic.data.database.entity.BookEntity

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookInfo: BookEntity)

    @Update
    suspend fun update(bookInfo: BookEntity)

    @Delete
    suspend fun delete(bookInfo: BookEntity)

    @Query("SELECT * FROM BookEntity")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    suspend fun getById(id: Int): BookEntity
}