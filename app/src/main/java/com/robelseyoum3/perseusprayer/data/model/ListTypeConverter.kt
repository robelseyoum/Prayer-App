package com.robelseyoum3.perseusprayer.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object ListTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMutableList(mString: String): MutableList<String>? {
        if (mString.isBlank()) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(mString, type)
    }


    @TypeConverter
    @JvmStatic
    fun mutableListToString(mString: MutableList<String>): String{
        if(mString.isEmpty()){
            return ""
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MutableList<String>>() {}.type
        return gson.toJson(mString, type)
    }

}