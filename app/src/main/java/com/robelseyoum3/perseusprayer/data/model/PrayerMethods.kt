package com.robelseyoum3.perseusprayer.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "prayer_methods")
@Parcelize
data class PrayerMethods(

    @PrimaryKey(autoGenerate = true) val pk: Int,
    @ColumnInfo(name = "methodBased") val methodBased: MutableMap<String, String>) : Parcelable {

    operator fun get(s: String): Any {
        return methodBased
    }

}