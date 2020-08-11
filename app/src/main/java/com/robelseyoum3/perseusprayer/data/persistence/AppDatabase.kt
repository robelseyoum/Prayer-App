package com.robelseyoum3.perseusprayer.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.robelseyoum3.perseusprayer.data.model.*

@Database(entities = [PrayerMethods::class, PrayerTimes::class], version = 4)
@TypeConverters(MapTypeConverter::class, ListTypeConverter::class, AzanTimeConverter::class, DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPrayerMethodsDao(): PrayerMethodsDao

    abstract fun getPrayerTimesDao(): PrayerTimesDao

    companion object {
        const val DATABASE_NAME: String = "application_database"
    }
}