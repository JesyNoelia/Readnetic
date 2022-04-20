package com.acoders.readnetic.data.database.entity

import androidx.room.*
import com.acoders.readnetic.data.typeconverters.Converters

@Entity
data class BookEntity(
    @TypeConverters(Converters::class)

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "authors")
    var authors: MutableList<String?>?,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    //@ColumnInfo(name = "isbn")
    //val isbn: MutableList<IndustryIdentifiers>?
)


