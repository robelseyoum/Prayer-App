package com.robelseyoum3.perseusprayer.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.robelseyoum3.perseusprayer.data.model.AzanTime
import com.robelseyoum3.perseusprayer.data.model.Latlong
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._EGYPT_SURVEY
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._FIXED_ISHAA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._KARACHI_HANAF
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._MUSLIM_LEAGUE
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._NORTH_AMERICA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._UMM_ALQURRA
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
   private val prayerTimesDao: PrayerTimesDao,
   private val prayerMethodsDao: PrayerMethodsDao
) {
    private val currentDate = SimpleDate(GregorianCalendar())
    var repo: MutableLiveData<Resource<PrayerTimes>> = MutableLiveData()


    fun getPrayersTimes(_coordination: Latlong, methodType: String?) {

        val today = SimpleDate(GregorianCalendar())
        val location = Location(
            _coordination.latitude,
            _coordination.longitude,
            2.0,
            0
        )

         //create default method of calculation
        val azan = Azan(location, checkPrayerBased(methodType))
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)

        if (repo.value?.data == null){
            repo.value = Resource.Loading(null)
        }

        var azanTime = mutableListOf(
            AzanTime("Fajar", prayerTimes.fajr().toString(), 0),
            AzanTime("Zuhar", prayerTimes.thuhr().toString(), 0),
            AzanTime("Asr", prayerTimes.assr().toString(), 0),
            AzanTime("Magrib", prayerTimes.maghrib().toString(), 0),
            AzanTime("Isha", prayerTimes.ishaa().toString(), 0)
        )

        val times = PrayerTimes(
            currentDate.toString(),
            azanTime
        )

        repo.value = Resource.Success(times)

        CoroutineScope(IO).launch {
            prayerTimesDao.insertOnIgnore(times)
        }

    }

     fun saveMethodOfCalculationToDatabase(params: String) {
         CoroutineScope(IO ).launch {
             val prayerMethods = PrayerMethods(mutableMapOf(("prayerMethod" to params)))
             prayerMethodsDao.insertOnIgnore(prayerMethods)
         }
     }

    private fun checkPrayerBased(methodType: String?): Method {
        return when (methodType) {
            _EGYPT_SURVEY -> Method.EGYPT_SURVEY
            _FIXED_ISHAA -> Method.FIXED_ISHAA
            _KARACHI_HANAF -> Method.KARACHI_HANAF
            _MUSLIM_LEAGUE -> Method.MUSLIM_LEAGUE
            _NORTH_AMERICA -> Method.NORTH_AMERICA
            _UMM_ALQURRA -> Method.UMM_ALQURRA
            else -> Method.NONE
        }
    }

}