package com.acoders.readnetic.ui.view.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.toError
import com.acoders.readnetic.usecase.FindBookByIsbnUseCase
import com.acoders.readnetic.usecase.SwitchBookFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val findBookByIsbnUseCase: FindBookByIsbnUseCase,
    private val switchBookFavoriteUseCase: SwitchBookFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun loadIsbn(isbn: Int) {
        viewModelScope.launch {
            Log.d("***safeargs", isbn.toString())
            findBookByIsbnUseCase(isbn)
                .catch { cause -> _state.update {
                    Log.d("***books", it.toString())
                    it.copy(error = cause.toError()) } }
                .collect { book -> _state.update {
                    Log.d("***books", it.toString())
                    UiState(book = book) } }
        }
    }

    /*fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.book?.let { book ->
                val error = switchBookFavoriteUseCase(book)
                _state.update { it.copy(error = error) }

            }
        }
    }*/

    data class UiState(val book: Book? = null, val error: Error? = null)
}

