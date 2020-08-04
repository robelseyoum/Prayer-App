package com.robelseyoum3.perseusprayer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "prayer_times")
data class PrayerTimes (

    @TypeConverters(DateTimeConverter::class)
    @ColumnInfo(name = "dateTimes") var dateTimes: MutableList<DateTimes>,

    @TypeConverters(AzanTimeConverter::class)
    @ColumnInfo(name = "azanTimes") var azanTimes: MutableList<AzanTime>
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}
