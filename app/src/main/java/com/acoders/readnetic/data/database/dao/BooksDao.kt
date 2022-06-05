package com.acoders.readnetic.data.database.dao

import androidx.room.*
import com.acoders.readnetic.data.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookInfo: List<BookEntity>)

    @Update
    suspend fun update(bookInfo: BookEntity)

    @Delete
    suspend fun delete(bookInfo: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
   fun getById(id: Int): Flow<BookEntity>
}