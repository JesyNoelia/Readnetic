package com.acoders.readnetic.data.network

import com.acoders.readnetic.data.network.model.googleBooksModel.ResponseGoogleBooks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiClientGoogleBooks {

    @GET("volumes")
    suspend fun getAllBooksByISBN(
        @Query("q") isbn: String,
        @Query("key") apiKey: String = API_KEY_GoogleBooks
    ): Response<ResponseGoogleBooks>

    @GET("volumes")
    suspend fun getAllBooksByAnyData(
        @Query("q") data: String,
        @Query("key") apiKey: String = API_KEY_GoogleBooks
    ): Response<ResponseGoogleBooks>
}

const val API_KEY_GoogleBooks = "AIzaSyBmzUxVm8o88z-5Yzcu1bqQpqgEA3CWH1w"
