package com.acoders.readnetic.data.network

import com.acoders.readnetic.data.network.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiClient {

    @GET("volumes")
    suspend fun getAllBooksByISBN(
        @Query("q") isbn: String,
        @Query("key") apiKey: String = API_KEY
    ): Response<BookResponse>

    @GET("volumes")
    suspend fun getAllBooksByAnyData(
        @Query("q") isbn: String,
        @Query("key") apiKey: String = API_KEY
    ): Response<BookResponse>
}

const val API_KEY = "AIzaSyBmzUxVm8o88z-5Yzcu1bqQpqgEA3CWH1w"
