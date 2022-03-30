package com.acoders.readnetic.data.network.model.googleBooksModel

import com.google.gson.annotations.SerializedName


data class SearchInfo (

  @SerializedName("textSnippet" ) var textSnippet : String? = null

)