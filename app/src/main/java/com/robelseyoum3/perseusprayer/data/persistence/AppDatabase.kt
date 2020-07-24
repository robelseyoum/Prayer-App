package com.robelseyoum3.perseusprayer.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes

@Database(entities = [PrayerMethods::class, PrayerTimes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPrayerMethodsDao(): PrayerMethodsDao

    abstract fun getPrayerTimesDao(): PrayerTimesDao

    companion object {
        val DATABASE_NAME: String = "app_db"
    }
}