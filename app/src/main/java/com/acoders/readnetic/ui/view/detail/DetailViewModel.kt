package com.acoders.readnetic.ui.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.toError
import com.acoders.readnetic.usecase.FindBookByIdUseCase
import com.acoders.readnetic.usecase.SwitchBookFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    bookId: Int,
    findBookByIdUseCase: FindBookByIdUseCase,
    private val switchBookFavoriteUseCase: SwitchBookFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findBookByIdUseCase(bookId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { book -> _state.update { UiState(book = book) } }
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.book?.let { book ->
                val error = switchBookFavoriteUseCase(book)
                _state.update { it.copy(error = error) }

            }
        }
    }

    data class UiState(val book: Book? = null, val error: Error? = null)
}

