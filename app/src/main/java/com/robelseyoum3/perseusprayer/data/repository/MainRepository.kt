package com.robelseyoum3.perseusprayer.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
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
   private val prayerMethodsDao: PrayerMethodsDao) {

      var _repoPrayerTime: MutableLiveData<Resource<PrayerTimes>> = MutableLiveData()

    var job: CompletableJob? = null

     fun getPrayersTimes(_coordination: Latlong, methodType: String?) {
        job = Job()


        val today = SimpleDate(GregorianCalendar())
        val location = Location(
            _coordination.latitude ?: 0.0,
            _coordination.longitude ?: 0.0,
            2.0,
            0
        )

        var prayerBasedMethod = checkPrayerBased(methodType)

         //create default method of calculation
        val azan = Azan(location, prayerBasedMethod)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)



        if(_repoPrayerTime.value?.data == null){
            _repoPrayerTime.value = Resource.Loading(null)
        }

        job?.let { theJob ->
            CoroutineScope(IO + theJob).launch {
                withContext(Main){

                    if(_coordination == null){
                        _repoPrayerTime.value = Resource.Error("No Prayer Data Found", null)
                    }else {

                        _repoPrayerTime.value = Resource.Success(
                            PrayerTimes(
                                mutableListOf(
                                    today.day.toString(),
                                    today.month.toString(),
                                    today.year.toString()
                                ),
                                imsaak.toString(),
                                prayerTimes.fajr().toString(),
                                prayerTimes.shuruq().toString(),
                                prayerTimes.thuhr().toString(),
                                prayerTimes.assr().toString(),
                                prayerTimes.maghrib().toString(),
                                prayerTimes.ishaa().toString()
                            )
                        )

                        withContext(IO) {
                            //don't care about result, Just insert if it doesn't exist b/c foreign key relationship
                            prayerTimesDao.insertOnIgnore(
                                PrayerTimes(
                                    mutableListOf(
                                        today.day.toString(),
                                        today.month.toString(),
                                        today.year.toString()
                                    ),
                                    imsaak.toString(),
                                    prayerTimes.fajr().toString(),
                                    prayerTimes.shuruq().toString(),
                                    prayerTimes.thuhr().toString(),
                                    prayerTimes.assr().toString(),
                                    prayerTimes.maghrib().toString(),
                                    prayerTimes.ishaa().toString()
                                )
                            )
                        }
                    }

                    theJob.complete()
                }
            }
        }
    }




     fun saveMethodOfCalculationToDatabase(params: String) {
         job = Job()

         job?.let { theJob ->
             CoroutineScope(IO + theJob).launch {
                 val prayerMethods = PrayerMethods(mutableMapOf(("prayerMethod" to params)))
                 prayerMethodsDao.insertOnIgnore(prayerMethods)
             }
             theJob.complete()
         }
    }

    //store the method into shared preference for methodsd
//    private fun saveMethodOfCalculationToPrefs(prayerMethods: PrayerMethods) {
//        prayerMethods.methodBased?.forEach { (key, value) ->
//            sharedPrefsEditor.putString(key, value)
//            sharedPrefsEditor.apply()
//        }
//    }




//    private fun checkPrayerBased(methodType: PrayerMethods) : Method {
//
//        return when (methodType) {
//            methodType["EGYPT_SURVEY"] -> { Method.EGYPT_SURVEY }
//            methodType["FIXED_ISHAA"] -> Method.FIXED_ISHAA
//            methodType["KARACHI_HANAF"] -> Method.KARACHI_HANAF
//            methodType["MUSLIM_LEAGUE"] -> Method.MUSLIM_LEAGUE
//            methodType["NORTH_AMERICA"] -> Method.NORTH_AMERICA
//            methodType["UMM_ALQURRA"] -> Method.UMM_ALQURRA
//            else -> Method.NONE
//        }
//    }

    private fun checkPrayerBased(methodType: String?) : Method {
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

    fun cancelJobs(){
        job?.cancel()
    }

}