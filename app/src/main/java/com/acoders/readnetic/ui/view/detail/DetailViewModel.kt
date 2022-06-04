package com.acoders.readnetic.ui.view.detail

import androidx.lifecycle.ViewModel
import com.acoders.readnetic.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(bookId: Int): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(val book: Book? = null, val error: Error? = null)
}