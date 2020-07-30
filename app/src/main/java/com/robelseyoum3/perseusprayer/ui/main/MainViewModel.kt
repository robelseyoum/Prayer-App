package com.robelseyoum3.perseusprayer.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.robelseyoum3.perseusprayer.data.model.Latlong
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.repository.MainRepository
import com.robelseyoum3.perseusprayer.utils.PreferenceKeys
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException
import javax.inject.Inject

class MainViewModel  @Inject constructor(val mainRepository: MainRepository, val prayerMethodsDao: PrayerMethodsDao) : ViewModel()  {

    var _coordination: MutableLiveData<Latlong> = MutableLiveData()
    var _prayerMethod: MutableLiveData<String> = MutableLiveData()
    var _loading: MutableLiveData<Boolean> = MutableLiveData()

    val _prayer: LiveData<Resource<PrayerTimes>> = mainRepository._repoPrayerTime

    fun getPrayerTimes(){
        mainRepository.getPrayersTimes(_coordination.value!!, _prayerMethod.value)
    }

    fun setLocationCoordination(latitude: Double, longitude: Double) {
        _coordination.value = Latlong(latitude, longitude)
        toggleLoading(false)
    }

    fun setPrayerMethod(prayerMethod: String) {
        _prayerMethod.value = prayerMethod
    }

    fun toggleLoading(loading: Boolean){
        _loading.value = loading
    }

    fun initPrayerMethodModel(){
        val prayerMethodKey =  "KARACHI_HANAF"

        CoroutineScope(IO).launch {
//            prayerMethodsDao.deleteAllPrayerMethod()
            val result = prayerMethodsDao.selectAllPrayerMethod()

            withContext(Main){

                if (result == null) {
                    //add default prayer method
                    mainRepository.saveMethodOfCalculationToDatabase(prayerMethodKey)
                    _prayerMethod.value = prayerMethodKey
                } else {
                    _prayerMethod.value = result.methodBased["prayerMethod"]
                }

            }
        }
    }

    fun cancelActiveJobs(){
        mainRepository.cancelJobs()
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }


}