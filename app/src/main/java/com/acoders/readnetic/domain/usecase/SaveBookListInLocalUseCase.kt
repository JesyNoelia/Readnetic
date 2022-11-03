package com.acoders.readnetic.domain.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book
import javax.inject.Inject

class SaveBookListInLocalUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke(bookList: List<Book>) = repository.saveLocalBestsellers(bookList)
}