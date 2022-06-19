package com.acoders.readnetic.data.network.model.googleBooksModel

import com.google.gson.annotations.SerializedName


data class ResponseGoogleBooks (

  @SerializedName("kind"       ) var kind       : String?          = null,
  @SerializedName("totalItems" ) var totalItems : Int?             = null,
  @SerializedName("items"      ) var items      : List<BookGoogleBooks> = emptyList()

)