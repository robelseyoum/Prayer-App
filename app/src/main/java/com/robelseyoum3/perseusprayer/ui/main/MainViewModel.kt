package com.robelseyoum3.perseusprayer.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azan.Azan
import com.azan.Method
import com.azan.PrayerTime
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import java.util.*
import javax.inject.Inject

class MainViewModel  @Inject constructor() : ViewModel()  {


    var coordinations: MutableLiveData<MutableMap<String, Double>> = MutableLiveData()
    var prayerTimeMutableLiveData: MutableLiveData<PrayerTimes> = MutableLiveData()

    fun getLocationCoordination(latitude: String, longitude: String) {
        coordinations.value = mutableMapOf(("latitude" to latitude.toDouble()),("longitude" to longitude.toDouble()))
    }

    fun getPrayersTimes() {
        val today = SimpleDate(GregorianCalendar())
        val location = Location(
            coordinations.value?.get("latitude") ?: 0.0,
            coordinations.value?.get("longitude") ?: 0.0,
            2.0,
            0
        )

        val azan = Azan(location, Method.EGYPT_SURVEY)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)

        prayerTimeMutableLiveData.value =  PrayerTimes(mutableListOf(today.day.toString(), today.month.toString(), today.year.toString()),
                                            imsaak.toString(),
                                            prayerTimes.fajr().toString(),
                                            prayerTimes.shuruq().toString(),
                                            prayerTimes.thuhr().toString(),
                                            prayerTimes.assr().toString(),
                                            prayerTimes.maghrib().toString(),
                                            prayerTimes.ishaa().toString()
                                        )
    }


}