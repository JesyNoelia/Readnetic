package com.acoders.readnetic.di

import android.content.Context
import androidx.room.Room
import com.acoders.readnetic.data.database.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesBooksDatabase(@ApplicationContext context: Context): BooksDatabase {
        return Room.databaseBuilder(context, BooksDatabase::class.java, BooksDatabase.DB_NAME).build()
    }
    @Singleton
    @Provides
    fun providesBooksDao(booksDatabase: BooksDatabase)= booksDatabase.booksDao()
}