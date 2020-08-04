package com.robelseyoum3.perseusprayer.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object AzanTimeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMutableList(mString: String): MutableList<AzanTime> {
        val gson = Gson()
        val type = object : TypeToken<List<AzanTime>>() {}.type
        return gson.fromJson(mString, type)
    }

    @TypeConverter
    @JvmStatic
    fun mutableListToString(mString: MutableList<AzanTime>): String{
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<AzanTime>>() {}.type
        return gson.toJson(mString, type)
    }
}