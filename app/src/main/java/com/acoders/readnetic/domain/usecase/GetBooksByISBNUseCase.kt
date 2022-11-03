package com.acoders.readnetic.domain.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book
import javax.inject.Inject

class GetBooksByISBNUseCase @Inject constructor(private val repository: BookRepository) {
    operator fun invoke(isbn: String) = repository.getLocalBookByIsbn(isbn)
}
