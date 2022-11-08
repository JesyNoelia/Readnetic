package com.acoders.readnetic.data.network.model

import com.google.gson.annotations.SerializedName


data class Lists (

  @SerializedName("list_id"           ) var listId          : Int?             = null,
  @SerializedName("list_name"         ) var listName        : String?          = null,
  @SerializedName("list_name_encoded" ) var listNameEncoded : String?          = null,
  @SerializedName("display_name"      ) var displayName     : String?          = null,
  @SerializedName("updated"           ) var updated         : String?          = null,
  @SerializedName("list_image"        ) var listImage       : String?          = null,
  @SerializedName("list_image_width"  ) var listImageWidth  : String?          = null,
  @SerializedName("list_image_height" ) var listImageHeight : String?          = null,
  @SerializedName("books"             ) var books           : List<BookNyt> = emptyList()

)