package com.acoders.readnetic.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
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
    private val updateFavoriteBookInLocalUseCase: UpdateFavoriteBookInLocalUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

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
                .catch { println("There was a problem: $it") }
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
                .catch { println("There was a problem: $it") }
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
                .catch { println("There was a problem: $it") }
                .collect {
                    println("The updated local list is: $it")
                    _state.value = UiState(books = it)
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
                if (book.isbn == favoriteBook) {
                    val bookToUpdate = book.copy(
                        favorite = true
                    )
                    updateFavoriteBookInLocalUseCase(bookToUpdate)
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val books: List<Book>? = null,
        val error: Error? = null
    )
}
