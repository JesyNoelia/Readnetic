package com.acoders.readnetic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.acoders.readnetic.data.database.dao.BooksDao
import com.acoders.readnetic.data.database.entity.BookEntity
import com.acoders.readnetic.data.typeconverters.Converters

@Database(entities = [BookEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class BooksDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "BooksDB"
    }

    abstract fun booksDao(): BooksDao
}