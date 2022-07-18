package com.acoders.readnetic.data.network.model.nytmodel

import com.google.gson.annotations.SerializedName


data class BookNyt(

  @SerializedName("age_group"            ) var ageGroup           : String?             = null,
  @SerializedName("amazon_product_url"   ) var amazonProductUrl   : String?             = null,
  @SerializedName("article_chapter_link" ) var articleChapterLink : String?             = null,
  @SerializedName("author"               ) var author             : String             ,
  @SerializedName("book_image"           ) var bookImage          : String             ,
  @SerializedName("book_image_width"     ) var bookImageWidth     : Int?                = null,
  @SerializedName("book_image_height"    ) var bookImageHeight    : Int?                = null,
  @SerializedName("book_review_link"     ) var bookReviewLink     : String?             = null,
  @SerializedName("book_uri"             ) var bookUri            : String?             = null,
  @SerializedName("contributor"          ) var contributor        : String?             = null,
  @SerializedName("contributor_note"     ) var contributorNote    : String?             = null,
  @SerializedName("created_date"         ) var createdDate        : String             ,
  @SerializedName("description"          ) var description        : String             ,
  @SerializedName("first_chapter_link"   ) var firstChapterLink   : String?             = null,
  @SerializedName("price"                ) var price              : String?             = null,
  @SerializedName("primary_isbn10"       ) var primaryIsbn10      : String             ,
  @SerializedName("primary_isbn13"       ) var primaryIsbn13      : String             ,
  @SerializedName("publisher"            ) var publisher          : String?             = null,
  @SerializedName("rank"                 ) var rank               : Int?                = null,
  @SerializedName("rank_last_week"       ) var rankLastWeek       : Int?                = null,
  @SerializedName("sunday_review_link"   ) var sundayReviewLink   : String?             = null,
  @SerializedName("title"                ) var title              : String             ,
  @SerializedName("updated_date"         ) var updatedDate        : String             ,
  @SerializedName("weeks_on_list"        ) var weeksOnList        : Int?                = null,
  @SerializedName("buy_links"            ) var buyLinks           : ArrayList<BuyLinks> = arrayListOf()

)