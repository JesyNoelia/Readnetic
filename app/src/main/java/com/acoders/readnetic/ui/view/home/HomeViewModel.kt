package com.acoders.readnetic.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.toError
import com.acoders.readnetic.usecase.GetBestsellersUseCase
import com.acoders.readnetic.usecase.GetPopularBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBestSellersUseCase: GetBestsellersUseCase,
    private val getPopularBooksUseCase: GetPopularBooksUseCase,

): ViewModel(){

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPopularBooksUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect {books -> _state.update { UiState(books = books) } }
        }
    }

    fun onUiReady(){
        viewModelScope.launch {
            _state.value = UiState(loading = false)
           val error = getBestSellersUseCase()
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val books: List<Book>? = null,
        val error: Error? = null
    )
}