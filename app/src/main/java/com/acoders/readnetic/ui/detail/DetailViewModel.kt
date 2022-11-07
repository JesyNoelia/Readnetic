package com.acoders.readnetic.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acoders.readnetic.domain.Book
import com.acoders.readnetic.domain.Error
import com.acoders.readnetic.domain.usecase.GetBookListFromLocalUseCase
import com.acoders.readnetic.domain.usecase.UpdateFavoriteBookInLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val updateFavoriteBookInLocalUseCase: UpdateFavoriteBookInLocalUseCase,
    private val getBookListFromLocalUseCase: GetBookListFromLocalUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun updateFavoriteProperty(isFavorite: Boolean) {
        viewModelScope.launch {
            _state.value.book?.let { book ->
                val bookToUpdate = Book(
                    isbn = book.isbn,
                    authors = book.authors,
                    title = book.title,
                    description = book.description,
                    bookPicture = book.bookPicture,
                    date = book.date,
                    favorite = isFavorite
                )

                println("***favorite: $bookToUpdate")
                try{updateFavoriteBookInLocalUseCase(bookToUpdate).apply {
                    saveItem(bookToUpdate)
                }
                } catch (e: Exception){  println("El error es: $e")}

                getBookListFromLocalUseCase().collect{
                    println("Este es el listado justo despues de actualizar la db: $it")
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
