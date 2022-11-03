package com.acoders.readnetic.domain.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book
import javax.inject.Inject

class UpdateFavoriteBookInLocalUseCase @Inject constructor (private val repository: BookRepository) {
    suspend operator fun invoke(book: Book) = repository.updateFavoriteBestSeller(book)
}