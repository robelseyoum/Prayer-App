package com.robelseyoum3.perseusprayer.data.repository

import com.azan.Azan
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.robelseyoum3.perseusprayer.data.model.*
import com.robelseyoum3.perseusprayer.utils.checkPrayerBased
import com.robelseyoum3.perseusprayer.utils.prayerNames
import java.util.*

class PrayerTimeRepo : IPrayerTime {

    override fun getPrayersTimes(latLng: LatLng, prayerTimeMethod: String?): PrayerTimes {
        val currentDate = SimpleDate(GregorianCalendar())
        val location = Location(latLng.latitude, latLng.longitude, 2.0, 0)
        val azan = Azan(location, checkPrayerBased(prayerTimeMethod))
        val azanTimes = azan.getPrayerTimes(currentDate)

        val dateTime = mutableListOf(
            DateTimes(
                currentDate.day.toString(),
                currentDate.month.toString(),
                currentDate.year.toString()
            )
        )

        val azanTime = mutableListOf<AzanTime>()

        for(i in 0..5){
            if( i == 1){
                azanTime.add(
                    AzanTime(
                        prayerNames[i] ?: error(""),
                        azanTimes.fajr().toString(),
                        1
                    )
                )
            } else {
                azanTime.add(
                    AzanTime(
                        prayerNames[i] ?: error(""),
                        azanTimes.fajr().toString(),
                        0
                    )
                )
            }
        }

        return PrayerTimes(dateTime, azanTime)
    }
}