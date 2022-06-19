package com.acoders.readnetic.data.network.model

import com.google.gson.annotations.SerializedName


data class BookResponse (

  @SerializedName("kind"       ) var kind       : String?          = null,
  @SerializedName("totalItems" ) var totalItems : Int?             = null,
  @SerializedName("items"      ) var items      : List<BookAPI> = emptyList()

)