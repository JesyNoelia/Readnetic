package com.acoders.readnetic.data


import com.acoders.readnetic.data.datasource.BookLocalDataSource
import com.acoders.readnetic.data.network.BookService
import com.acoders.readnetic.ui.view.extensions.toBook
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.tryCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BookRepository @Inject constructor(
    private val service: BookService,
    private val localDataSource: BookLocalDataSource
    ) {

    val popularBooks= localDataSource.books

    /*suspend fun getBooksByISBN(isbn: String): Resource<List<Book>> {
        val bookList = service.getBooksByISBN(isbn)
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }

    suspend fun getBooksByAnyData(data: String): Resource<List<Book>> {
        val bookList = service.getBooksByAnyData(data)
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }*/

    suspend fun getBestsellers(): Error? = tryCall {
            if (localDataSource.isEmpty()) {
                val books = service.getBestsellers()
                books.data?.let { localDataSource.save(it.map { it.toBook() }) }
            }
        }

    /*suspend fun getBestsellers(): Resource<List<Book>> {
        val bookList = service.getBestsellers()
        return when (bookList.data) {
            null -> Resource.Error(bookList.message ?: "Something went wrong")
            else -> Resource.Success(bookList.data.map { it.toBook() })
        }
    }*/

    suspend fun switchFavorite(book: Book): Error? =
        tryCall {
            val updatedBook = book.copy(favorite = !book.favorite!!)
            return localDataSource.save(listOf(updatedBook))
        }

    fun findById(id: Int): Flow<Book> = localDataSource.findById(id)

    //suspend fun updateDB() {}
}
