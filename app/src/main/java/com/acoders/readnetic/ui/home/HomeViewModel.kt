package com.acoders.readnetic.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.toError
import com.acoders.readnetic.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBookListFromRemoteUseCase: GetBookListFromRemoteUseCase,
    private val getBookListFromLocalUseCase: GetBookListFromLocalUseCase,
    private val saveBookListInLocalUseCase: SaveBookListInLocalUseCase,
    private val updateFavoriteBookInLocalUseCase: UpdateFavoriteBookInLocalUseCase,
    private val getBookByISBNUseCase: GetBookByISBNUseCase,
) : ViewModel() {

    private val _state = MutableSharedFlow<UiState>()
    val state: SharedFlow<UiState> = _state.asSharedFlow()

    private val favoriteList: MutableList<String> = mutableListOf()
    private var isDbEmpty: Boolean = false

    init {
        updateUI()
    }

    private fun updateUI() {
        saveFavoritesFromLocal()
        updateBookListFromRemote()
        getBookListFromLocal()
    }

    private fun saveFavoritesFromLocal() {
        viewModelScope.launch {
            getBookListFromLocalUseCase()
                .catch { cause ->
                    _state.emit(UiState(error = cause.toError()) )
                }
                .collect {
                    println("The local list is: $it")
                    if (it.isEmpty()) isDbEmpty = true
                    saveFavoriteBookList(it)
                }
        }
    }

    private fun updateBookListFromRemote() {
        viewModelScope.launch {
            getBookListFromRemoteUseCase()
                .catch { cause ->
                    _state.emit(UiState(error = cause.toError()) )
                }
                .collect {
                    updateFavoriteBookList(it)
                    println("The remote list is: $it")
                    if (isDbEmpty) {
                        saveBookListInLocalUseCase(it)
                    }
                }
        }
    }

    private fun getBookListFromLocal() {
        viewModelScope.launch {
            getBookListFromLocalUseCase()
                .catch { cause ->
                    _state.emit(UiState(error = cause.toError()) )
                }
                .collect { books ->
                    _state.emit(UiState(books = books))
                        println("The updated local list is: $books")
                }
        }
    }

    private fun saveFavoriteBookList(list: List<Book>) {
        if (list.isNotEmpty()) {
            list.forEach {
                if (it.favorite) {
                    favoriteList.add(it.isbn)
                    println("${it.title} saved as favorite")
                }
            }
        }
        favoriteList.clear()
    }

    private suspend fun updateFavoriteBookList(list: List<Book>) {
        list.forEach { book ->
            favoriteList.forEach { favoriteBook ->
                println("book isbn: ${book.isbn}")
                println("favoriteBook: $favoriteBook")
                if (book.isbn == favoriteBook) {
                    val bookToUpdate = book.copy(favorite = true)
                    updateFavoriteBookInLocalUseCase(bookToUpdate)
                }
            }
        }
        println("La lista de favoritos es: $favoriteList")
    }

    fun loadIsbn(isbn: String) {
        viewModelScope.launch {
            Log.d("***QRScan", isbn)
            getBookByISBNUseCase(isbn)
                .catch { cause ->
                    _state.emit(UiState(book = null) )
                    Log.d("***errorLoadIsbn", cause.toString())
                }
                .collect { book ->
                    _state.emit(UiState(book = book))
                        Log.d("***book", book.toString())
                }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val books: List<Book>? = null,
        val book: Book? = null,
        val error: Error? = null
    )
}
