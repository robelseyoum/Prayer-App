package com.robelseyoum3.perseusprayer.ui.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.repository.MainRepository
import com.robelseyoum3.perseusprayer.utils.PreferenceKeys
import com.robelseyoum3.perseusprayer.utils.Resource
import javax.inject.Inject

class MainViewModel  @Inject constructor(val mainRepository: MainRepository, val sharedPreferences: SharedPreferences, val prayerMethodsDao: PrayerMethodsDao) : ViewModel()  {

    var _coordination: MutableLiveData<MutableMap<String, Double>> = MutableLiveData()
    var _prayerMethod: MutableLiveData<String> = MutableLiveData()


    val _prayer: LiveData<Resource<PrayerTimes>> = Transformations
        .switchMap(_coordination){
            mainRepository.getPrayersTimes(it,_prayerMethod.value)
        }

    fun setLocationCoordination(latitude: String, longitude: String) {
        val update_lat_long = mutableMapOf(("latitude" to latitude.toDouble()),("longitude" to longitude.toDouble()))
        if(_coordination.value == update_lat_long){
            return
        }
        _coordination.value = update_lat_long
    }

    fun setPrayerMethod(prayerMethod: String) {
        _prayerMethod.value = prayerMethod
    }

    /**
    initialize() {

    if PrayerMethod is in Database {
    fetch the prayer method from db and set it to prayerMethod in viewModel
    } else {
    set the default prayer method in room db
    set the prayerMethod in viewModel
    }
    }
     */

    fun initPrayerMethodModel(){

        val methodOfPrayer = mutableMapOf("KARACHI_HANAF" to "University of Islamic Sciences, Karachi (Hanafi)")
        val result = prayerMethodsDao.selectAllPrayerMethod()

        if(result.methodBased.isEmpty()){
            mainRepository.saveMethodOfCalculationToDatabase()
        }else {
//            prayerMethodsDao.insertAndReplace()
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