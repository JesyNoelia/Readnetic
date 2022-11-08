package com.acoders.readnetic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acoders.readnetic.data.database.dao.BooksDao
import com.acoders.readnetic.data.database.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BooksDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "BooksDB"
    }

    abstract fun booksDao(): BooksDao
}
