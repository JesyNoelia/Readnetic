package com.acoders.readnetic.data.datasource

import com.acoders.readnetic.data.network.BookApiClientNYT
import com.acoders.readnetic.data.network.model.BookNyt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(private val apiClientNYT: BookApiClientNYT) {
    suspend fun getBestsellers(): Flow<List<BookNyt>> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(apiClientNYT.getAllBooks().results.lists[0].books)
            }
        }
    }
}
