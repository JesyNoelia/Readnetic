package com.acoders.readnetic.domain.usecase

import com.acoders.readnetic.data.BookRepository
import javax.inject.Inject

class GetBookListFromRemoteUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke() = repository.getRemoteBestsellers()
}
