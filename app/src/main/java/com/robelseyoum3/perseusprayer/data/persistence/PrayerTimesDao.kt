package com.robelseyoum3.perseusprayer.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes


@Dao
interface PrayerTimesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndReplace(prayerTimes: PrayerTimes): Long


    @Insert(onConflict = OnConflictStrategy.IGNORE)  //id data is already exist ignore it
    fun insertOnIgnore(prayerTimes: PrayerTimes): Long


}