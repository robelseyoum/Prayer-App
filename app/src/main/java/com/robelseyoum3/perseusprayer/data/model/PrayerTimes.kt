package com.robelseyoum3.perseusprayer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "prayer_times")
data class PrayerTimes (

    @TypeConverters(ListTypeConverter::class)
    @ColumnInfo(name = "date") var date: String,

    @ColumnInfo(name = "azanTimes") var azanTimes: MutableList<AzanTime>
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}
