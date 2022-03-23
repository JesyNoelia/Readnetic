package com.acoders.readnetic.data.model

import com.acoders.readnetic.data.network.model.IndustryIdentifiers

data class Book(
    var authors:List<String>?,
    var title:String?,
    var description:String?,
    var bookPicture:String?,
    val date: String?,
   // val isbn: List<IndustryIdentifiers>?,
    //val pages: String,
    //val gender: String,
    //val profileAuthorPicture: String?
)
