package com.acoders.readnetic.usecase

import com.acoders.readnetic.data.BookRepository
import javax.inject.Inject

class FindBookByIdUseCase @Inject constructor (private val repository: BookRepository) {

    operator fun invoke(id: Int) = repository.findById(id)
}