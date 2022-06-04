package com.acoders.readnetic.domain

import com.acoders.readnetic.data.network.model.googleBooksModel.IndustryIdentifiers

data class Book(
    val id: Int,
    var authors: List<String?>?,
    var title: String?,
    var description: String?,
    var bookPicture: String?,
    val date: String?,
    val isbn: List<IndustryIdentifiers>?,
    val favorite: Boolean
    //val pages: String,
    //val gender: String,
    //val profileAuthorPicture: String?
)

