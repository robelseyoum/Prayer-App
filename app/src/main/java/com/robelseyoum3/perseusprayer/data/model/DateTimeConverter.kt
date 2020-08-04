package com.robelseyoum3.perseusprayer.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object DateTimeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMutableList(mString: String): MutableList<DateTimes> {
        val gson = Gson()
        val type = object : TypeToken<List<DateTimes>>() {}.type
        return gson.fromJson(mString, type)
    }

    @TypeConverter
    @JvmStatic
    fun mutableListToString(mString: MutableList<DateTimes>): String{
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<DateTimes>>() {}.type
        return gson.toJson(mString, type)
    }
}