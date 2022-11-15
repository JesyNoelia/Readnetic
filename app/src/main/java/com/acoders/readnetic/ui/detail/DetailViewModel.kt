package com.acoders.readnetic.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.usecase.UpdateFavoriteBookInLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateFavoriteBookInLocalUseCase: UpdateFavoriteBookInLocalUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun updateFavoriteProperty(isFavorite: Boolean) {
        viewModelScope.launch {
            _state.value.book?.let { book ->
                val bookToUpdate = book.copy(
                    favorite = isFavorite
                )

                println("***favorite: $bookToUpdate")
                updateFavoriteBookInLocalUseCase(bookToUpdate).apply {
                    saveItem(bookToUpdate)
                }
            }
        }
    }

    fun saveItem(book: Book) {
        viewModelScope.launch {
            _state.value = UiState(book)
        }
    }

    data class UiState(val book: Book? = null, val error: Error? = null)
}
