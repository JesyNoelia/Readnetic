package com.acoders.readnetic.data.model

import com.acoders.readnetic.data.network.model.IndustryIdentifiers

data class Book(
    var authors: List<String>? = emptyList(),
    var title: String? = null,
    var description: String? = null,
    var bookPicture: String? = null,
    val date: String? = null,
    val isbn: List<IndustryIdentifiers>? = emptyList(),
    //val pages: String,
    //val gender: String,
    //val profileAuthorPicture: String?
)
