package com.acoders.readnetic.domain.usecase

import com.acoders.readnetic.data.BookRepository
import javax.inject.Inject

class GetBooksByISBNUseCase @Inject constructor(private val repository: BookRepository) {
    operator fun invoke(isbn: String) = repository.getLocalBookByIsbn(isbn)
}
