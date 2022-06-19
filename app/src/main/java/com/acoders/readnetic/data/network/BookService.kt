package com.acoders.readnetic.data.network

import com.acoders.readnetic.data.network.model.googleBooksModel.BookGoogleBooks
import com.acoders.readnetic.data.network.model.googleBooksModel.ResponseGoogleBooks
import com.acoders.readnetic.data.network.model.nytmodel.BookNyt
import com.acoders.readnetic.data.network.model.nytmodel.Lists
import com.acoders.readnetic.data.network.model.nytmodel.ResponseNyt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class BookService @Inject constructor(
    private val apiGoogleBooks: BookApiClientGoogleBooks,
    private val apiNYT: BookApiClientNYT
) {

    suspend fun getBooksByISBN(isbn: String): Resource<List<BookGoogleBooks>> {
        return withContext(Dispatchers.IO) {
            val query = buildIsbnQuery(isbn)
            val responseGoogleBooks: Response<ResponseGoogleBooks> =
                apiGoogleBooks.getAllBooksByISBN(query)
            try {
                Resource.Success(responseGoogleBooks.body()?.items ?: emptyList())
            } catch (e: Exception) {
                Resource.Error("API error")
            }
        }
    }

    suspend fun getBooksByAnyData(anyData: String): Resource<List<BookGoogleBooks>> {
        return withContext(Dispatchers.IO) {
            val responseGoogleBooks: Response<ResponseGoogleBooks> =
                apiGoogleBooks.getAllBooksByAnyData(anyData)
            try {
                Resource.Success(responseGoogleBooks.body()?.items ?: emptyList())
            } catch (e: Exception) {
                Resource.Error("API error")
            }
        }
    }

    private fun buildIsbnQuery(isbn: String): String {
        return String.format("isbn:$isbn")
    }

    suspend fun getBestsellers(): Resource<List<BookNyt>> {
        return withContext(Dispatchers.IO) {
            val responseNyt: Response<ResponseNyt> = apiNYT.getAllBooks()
            try {
                val lists: List<Lists> = responseNyt.body()?.results?.lists ?: emptyList()
                Resource.Success(unifyAllBooksFromCategories(lists))
            } catch (e: Exception) {
                Resource.Error("API error")
            }
        }
    }

    private fun unifyAllBooksFromCategories(lists: List<Lists>): List<BookNyt>{
        val bookList: MutableList<BookNyt> = mutableListOf()
        lists.forEach {
            bookList.addAll(it.books)
        }
        return bookList
    }
}