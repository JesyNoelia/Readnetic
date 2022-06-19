package com.acoders.readnetic.data.network

import com.acoders.readnetic.data.network.model.BookAPI
import com.acoders.readnetic.data.network.model.BookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class BookService @Inject constructor(private val api: BookApiClient) {

    suspend fun getAllBooksByISBN(isbn: String): Resource<List<BookAPI>> {
        return withContext(Dispatchers.IO) {
            val query = buildIsbnQuery(isbn)
            val response: Response<BookResponse> = api.getAllBooksByISBN(query)
            try {
                Resource.Success(response.body()?.items ?: emptyList())
            } catch (e: Exception) {
                Resource.Error("API error")
            }
        }
    }

    suspend fun getAllBooksByAnyData(anyData: String): Resource<List<BookAPI>> {
        return withContext(Dispatchers.IO) {
            val response: Response<BookResponse> = api.getAllBooksByAnyData(anyData)
            try {
                Resource.Success(response.body()?.items ?: emptyList())
            } catch (e: Exception) {
                Resource.Error("API error")
            }
        }
    }

    private fun buildIsbnQuery(isbn: String): String {
        return String.format("isbn:$isbn")
    }
}