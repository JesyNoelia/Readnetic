package com.acoders.readnetic.data.model

data class Book(
    var author:String,
    var title:String,
    var description:String,
    var bookPicture:String,
    val date: String,
    val isbn: String,
    //val pages: String,
    //val gender: String,
    //val profileAuthorPicture: String?
)
