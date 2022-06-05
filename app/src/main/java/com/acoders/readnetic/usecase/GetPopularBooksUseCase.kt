package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.domain.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularBooksUseCase @Inject constructor(private val repository: BookRepository) {

    operator fun invoke(): Flow<List<Book>> = repository.popularBooks
}