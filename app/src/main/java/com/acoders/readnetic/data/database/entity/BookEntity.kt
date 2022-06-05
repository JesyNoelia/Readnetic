package com.acoders.readnetic.data.database.entity

import android.os.Parcelable
import androidx.room.*
import com.acoders.readnetic.data.typeconverters.Converters
import kotlinx.parcelize.Parcelize

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
    @ColumnInfo(name = "favorite")
    var favorite: Boolean,
    //@ColumnInfo(name = "isbn")
    //val isbn: MutableList<IndustryIdentifiers>?
)


