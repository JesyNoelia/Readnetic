package com.acoders.readnetic.data.network.model

import com.google.gson.annotations.SerializedName


data class Results (
  @SerializedName("bestsellers_date"           ) var bestsellersDate          : String?          = null,
  @SerializedName("published_date"             ) var publishedDate            : String?          = null,
  @SerializedName("published_date_description" ) var publishedDateDescription : String?          = null,
  @SerializedName("previous_published_date"    ) var previousPublishedDate    : String?          = null,
  @SerializedName("next_published_date"        ) var nextPublishedDate        : String?          = null,
  @SerializedName("lists"                      ) var lists                    : List<Lists> = emptyList()
)