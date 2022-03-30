package com.acoders.readnetic.data.network.model.nytmodel

import com.google.gson.annotations.SerializedName


data class BuyLinks (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("url"  ) var url  : String? = null

)