package com.robelseyoum3.perseusprayer.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "prayer_methods")
@Parcelize
data class PrayerMethods(

    @TypeConverters(MapTypeConverter::class)
    @ColumnInfo(name = "methodBased")
    val methodBased:
    MutableMap<String, String>

) : Parcelable {

    @PrimaryKey(autoGenerate = true) var pk: Int = 0

    operator fun get(s: String): Any {
        return methodBased
    }

}