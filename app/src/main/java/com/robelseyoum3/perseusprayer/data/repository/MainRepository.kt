package com.robelseyoum3.perseusprayer.data.repository

import androidx.lifecycle.MutableLiveData
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.robelseyoum3.perseusprayer.data.model.*
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
import java.util.*
import javax.inject.Inject
import kotlin.text.Typography.times

class MainRepository @Inject constructor(
    private val prayerTimesDao: PrayerTimesDao,
    private val prayerMethodsDao: PrayerMethodsDao
) {
    private val currentDate = SimpleDate(GregorianCalendar())
    lateinit var times: PrayerTimes

    var repo: MutableLiveData<Resource<PrayerTimes>> = MutableLiveData()

    fun getPrayersTimes(_coordination: LatLng, methodType: String?) {

        repo.value = Resource.Loading(null)

        val location = Location(
            _coordination.latitude,
            _coordination.longitude,
            2.0,
            0
        )
        //create default method of calculation
        val azan = Azan(location, checkPrayerBased(methodType))
        val azanTimes = azan.getPrayerTimes(currentDate)
        val imsaak = azan.getImsaak(currentDate)

        if (repo.value?.data == null){
            repo.value = Resource.Loading(null)
        }

        val dateTime = mutableListOf(
            DateTimes(
                currentDate.day.toString(),
                currentDate.month.toString(),
                currentDate.year.toString()
            )
        )

        val azanTime = mutableListOf(
            AzanTime("Fajar", azanTimes.fajr().toString(), 0),
            AzanTime("Sunrise", azanTimes.shuruq().toString(), 1),
            AzanTime("Zuhar", azanTimes.thuhr().toString(), 0),
            AzanTime("Asr", azanTimes.assr().toString(), 0),
            AzanTime("Magrib", azanTimes.maghrib().toString(), 0),
            AzanTime("Isha", azanTimes.ishaa().toString(), 0)
        )

        times = PrayerTimes(
            dateTime,
            azanTime
        )

        if(times.id.equals(null) && times.azanTimes.isNullOrEmpty() && times.dateTimes.isNullOrEmpty()){
            repo.value = Resource.Error("No Prayer date and time found")
        } else {
            repo.value = Resource.Success(times)
        }

    }

    fun savePrayerTimeToCache(){
        CoroutineScope(IO ).launch {
            prayerTimesDao.insertOnIgnore(times)
        }
    }

     fun saveMethodOfCalculationToCache(params: String) {
        CoroutineScope(IO ).launch {
            val prayerMethods = PrayerMethods(mutableMapOf(("prayerMethod" to params)))
            prayerMethodsDao.insertOnIgnore(prayerMethods)
        }
    }

    fun updatePrayerTimeFromCache(prayerMethod: String){
        CoroutineScope(IO).launch{
            val updatePrayerMethod = mutableMapOf(("prayerMethod" to prayerMethod))
            prayerMethodsDao.updatePrayerMethods(updatePrayerMethod)
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