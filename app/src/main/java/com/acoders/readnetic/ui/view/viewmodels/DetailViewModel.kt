package com.acoders.readnetic.ui.view.viewmodels

import androidx.lifecycle.ViewModel
import com.acoders.readnetic.data.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(bookId: Int): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(val book: Book? = null, val error: Error? = null)
}