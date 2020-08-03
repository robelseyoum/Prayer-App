package com.robelseyoum3.perseusprayer.data.repository

import androidx.lifecycle.MutableLiveData
import com.azan.Azan
import com.azan.AzanTimes
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.robelseyoum3.perseusprayer.data.model.AzanTime
import com.robelseyoum3.perseusprayer.data.model.LatLng
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val prayerTimesDao: PrayerTimesDao,
    private val prayerMethodsDao: PrayerMethodsDao
) {
    private val currentDate = SimpleDate(GregorianCalendar())
    var repo: MutableLiveData<Resource<PrayerTimes>> = MutableLiveData()

    fun getPrayersTimes(_coordination: LatLng, methodType: String?) {
        val location = Location(
            _coordination.latitude,
            _coordination.longitude,
            2.0,
            0
        )

        val azan = Azan(location, checkPrayerBased(methodType))
        val azanTimes = azan.getPrayerTimes(currentDate)
        val imsaak = azan.getImsaak(currentDate)

        if (repo.value?.data == null) {
            repo.value = Resource.Loading(null)
        }
// add date time format

//        var azanTimeList : List
//        loop till 5th index
//        AzanTime(
//            "Fajar",
//            "time"
//        )

        val times = PrayerTimes(
            currentDate.toString(),
            mutableListOf()
        )

        repo.value = Resource.Success(times)

        CoroutineScope(Dispatchers.IO).launch {
            prayerTimesDao.insertOnIgnore(times)
        }

    }

    fun savePrayerMethod(params: String) {
        CoroutineScope(Dispatchers.IO).launch {
            prayerMethodsDao.insertOnIgnore(
                PrayerMethods(mutableMapOf(("prayerMethod" to params)))
            )
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