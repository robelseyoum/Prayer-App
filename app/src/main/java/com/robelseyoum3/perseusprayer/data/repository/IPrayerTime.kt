package com.robelseyoum3.perseusprayer.data.repository

import com.robelseyoum3.perseusprayer.data.model.LatLng
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes

interface IPrayerTime {
    fun getPrayersTimes(latLng:LatLng, prayerTimeMethod:String?): PrayerTimes
}