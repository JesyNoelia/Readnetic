package com.acoders.readnetic.data.network.model.googleBooksModel

import com.google.gson.annotations.SerializedName


data class Epub (

  @SerializedName("isAvailable" ) var isAvailable : Boolean? = null

)