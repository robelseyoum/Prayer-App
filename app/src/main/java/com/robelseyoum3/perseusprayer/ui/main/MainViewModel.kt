package com.robelseyoum3.perseusprayer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import java.util.*
import javax.inject.Inject

class MainViewModel  @Inject constructor() : ViewModel()  {


    var coordinations: MutableLiveData<MutableMap<String, Double>> = MutableLiveData()

    var mDate: MutableLiveData<MutableList<String>> = MutableLiveData()
    var mImsaak: MutableLiveData<String> = MutableLiveData()
    var mFajr: MutableLiveData<String> = MutableLiveData()
    var mSunrise: MutableLiveData<String> = MutableLiveData()
    var mZuhr: MutableLiveData<String> = MutableLiveData()
    var mAsr: MutableLiveData<String> = MutableLiveData()
    var mMaghrib: MutableLiveData<String> = MutableLiveData()
    var mISHA: MutableLiveData<String> = MutableLiveData()

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

        mDate.value = mutableListOf(today.day.toString(), today.month.toString(), today.year.toString())
        mImsaak.value = imsaak.toString()
        mFajr.value = prayerTimes.fajr().toString()
        mSunrise.value = prayerTimes.thuhr().toString()
        mZuhr.value = prayerTimes.assr().toString()
        mAsr.value = prayerTimes.thuhr().toString()
        mMaghrib.value = prayerTimes.toString()
        mISHA.value = prayerTimes.toString()
    }

}