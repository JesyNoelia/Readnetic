package com.acoders.readnetic.data.network.model.nytmodel

import com.google.gson.annotations.SerializedName


data class ResponseNyt (

  @SerializedName("status"      ) var status     : String?  = null,
  @SerializedName("copyright"   ) var copyright  : String?  = null,
  @SerializedName("num_results" ) var numResults : Int?     = null,
  @SerializedName("results"     ) var results    : Results? = Results()

)