package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book
import javax.inject.Inject

class SwitchBookFavoriteUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun  invoke(book: Book) = repository.switchFavorite(book)
}