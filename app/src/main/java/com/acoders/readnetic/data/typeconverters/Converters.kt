package com.acoders.readnetic.data.typeconverters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.io.File.separator

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun toString(data: MutableList<String>?): String =
        if (data == null || data.isEmpty())
            ""
        else
            data.joinToString(separator = separator) { it }

    @TypeConverter
    fun fromString(data: String?): MutableList<String> {
        return data?.split(separator)?.toMutableList() ?: mutableListOf()
    }

}