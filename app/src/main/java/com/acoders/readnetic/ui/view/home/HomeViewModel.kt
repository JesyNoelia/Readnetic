package com.acoders.readnetic.ui.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.usecase.GetBestsellersUseCase
import com.acoders.readnetic.usecase.GetBooksByISBNUseCase
import com.acoders.readnetic.usecase.GetPopularBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBestSellersUseCase: GetBestsellersUseCase,
    private val getPopularBooksUseCase: GetPopularBooksUseCase,
    private val getBooksByISBNUseCase: GetBooksByISBNUseCase,
    private val repository: BookRepository

) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()


/*init {
viewModelScope.launch {
   val besellers = getBestSellersUseCase()
   if (bestsellers.data != null) {
       _state.value = UiState(books = bestsellers.data)
   } else {
       _state.value = UiState(error = bestsellers.message)
   }*/
    /*getPopularBooksUseCase()
        .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
        .collect {books -> _state.update { UiState(books = books) } }

}*/

    fun onUiReady() {
        viewModelScope.launch {
            Log.d("onUiReady","init")
            _state.value = UiState(loading = true)
            val books = getBestSellersUseCase()
            if (books.data == null) {
                val localBooks = repository.getLocalBestsellers()
                _state.value = UiState(books = localBooks, loading = false)
            } else {
                _state.value = UiState(books= books.data, loading = false)
                repository.saveLocalBestsellers(books.data)
            }
        }
    }


    data class UiState(
        val loading: Boolean = false,
        val books: List<Book>? = null,
        val error: String? = null
    )
}