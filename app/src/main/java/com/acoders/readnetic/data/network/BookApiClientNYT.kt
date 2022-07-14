package com.acoders.readnetic.data.network

import com.acoders.readnetic.data.network.model.nytmodel.ResponseNyt
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiClientNYT {

    @GET("full-overview.json")
    suspend fun getAllBooks(
        @Query("api-key") apiKey: String = API_KEY_NYT,
        @Query("published_date") offset: String = PUBLISHED_DATE
    ): Response<ResponseNyt>
}

const val API_KEY_NYT = "RtgyHpkAFR1iYk4kgpqg3xmz4vSt19zR"
const val PUBLISHED_DATE = "2012-05-26"
