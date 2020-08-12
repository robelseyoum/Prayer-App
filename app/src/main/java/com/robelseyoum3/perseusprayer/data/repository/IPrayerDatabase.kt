package com.robelseyoum3.perseusprayer.data.repository

import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes

interface IPrayerDatabase {
    fun savePrayerTime(prayerTimes: PrayerTimes)
    fun savePrayerMethod(prayerMethod: String)
    fun updatePrayerMethod(prayerMethod: String)
    suspend fun getPrayerMethod(): PrayerMethods
}