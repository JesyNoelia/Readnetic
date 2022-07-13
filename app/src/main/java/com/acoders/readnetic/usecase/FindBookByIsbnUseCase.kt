package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import javax.inject.Inject

class FindBookByIsbnUseCase @Inject constructor (private val repository: BookRepository) {

    operator fun invoke(isbn: Int) = repository.getLocalBookByIsbn(isbn)
}