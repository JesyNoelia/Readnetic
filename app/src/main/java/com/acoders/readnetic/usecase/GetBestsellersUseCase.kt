package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import com.acoders.readnetic.data.model.Book
import com.acoders.readnetic.data.network.Resource
import javax.inject.Inject

class GetBestsellersUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke(): Resource<List<Book>> {
        val response = repository.getBestsellers()
        return when (response.data) {
            null -> Resource.Error(response.message ?: "Something went wrong")
            else -> Resource.Success(response.data)
        }
    }
}