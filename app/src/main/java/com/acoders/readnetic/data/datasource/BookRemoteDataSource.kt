package com.acoders.readnetic.data.datasource

import com.acoders.readnetic.data.network.BookApiClientNYT
import com.acoders.readnetic.data.network.Resource
import com.acoders.readnetic.data.network.model.nytmodel.BookNyt
import com.acoders.readnetic.data.network.model.nytmodel.ResponseNyt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(private val apiClientNYT: BookApiClientNYT) {
    suspend fun getBestsellers(): Resource<List<BookNyt>> {
        return withContext(Dispatchers.IO) {
            try {
                val responseNyt: Response<ResponseNyt> = apiClientNYT.getAllBooks()
                val lists = responseNyt.body()?.results?.lists?.get(0)?.books ?: emptyList()
                Resource.Success(lists)
            } catch (e: Exception) {
                Resource.Error("API error")
            }
        }
    }
}