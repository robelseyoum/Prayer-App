package com.robelseyoum3.perseusprayer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "prayer_times")
data class PrayerTimes (

    @TypeConverters(ListTypeConverter::class)
    @ColumnInfo(name = "mDate") var mDate: MutableList<String>,

    @ColumnInfo(name = "mImsaak") var mImsaak: String?,

    @ColumnInfo(name = "mFajr") var mFajr: String?,

    @ColumnInfo(name = "mSunrise") var mSunrise: String?,

    @ColumnInfo(name = "mZuhr") var mZuhr: String?,

    @ColumnInfo(name = "mAsr") var mAsr: String?,

    @ColumnInfo(name = "mMaghrib") var mMaghrib: String?,

    @ColumnInfo(name = "mISHA") var mISHA: String?

){
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}
