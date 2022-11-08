package com.acoders.readnetic.domain.usecase

import com.acoders.readnetic.data.BookRepository
import javax.inject.Inject

class GetBookListFromLocalUseCase @Inject constructor(private val repository: BookRepository) {
    operator fun invoke() = repository.getLocalBestsellers()
}