package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book

class SwitchBookFavoriteUseCase(private val repository: BookRepository) {
    suspend operator fun  invoke(book: Book) = repository.switchFavorite(book)
}