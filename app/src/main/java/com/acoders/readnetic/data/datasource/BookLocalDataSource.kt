package com.acoders.readnetic.data.datasource


import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import kotlinx.coroutines.flow.Flow

interface BookLocalDataSource {
    val books: Flow<List<Book>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Book>
    suspend fun save(movies: List<Book>): Error?
}