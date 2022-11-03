package com.acoders.readnetic.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.toError
import com.acoders.readnetic.domain.usecase.GetBooksByISBNUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBooksByISBNUseCase: GetBooksByISBNUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun loadIsbn(isbn: String) {
         viewModelScope.launch {
            Log.d("***safeargs", isbn)
             getBooksByISBNUseCase(isbn)
                .catch { cause -> _state.update {
                    Log.d("***books", it.toString())
                    it.copy(error = cause.toError()) } }
                .collect { book -> _state.update {
                    Log.d("***books", it.toString())
                    UiState(book = book) } }
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {

        }
    }

    data class UiState(val book: Book? = null, val error: Error? = null)
}

