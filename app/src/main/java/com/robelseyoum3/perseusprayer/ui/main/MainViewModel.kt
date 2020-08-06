package com.robelseyoum3.perseusprayer.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.robelseyoum3.perseusprayer.data.model.LatLng
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

    var latlng: MutableLiveData<LatLng> = MutableLiveData()
    var prayerMethod: MutableLiveData<String> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val azanTime: LiveData<Resource<PrayerTimes>> = mainRepository.repo

    fun initPrayerMethodModel(){

        CoroutineScope(IO).launch {
            val method = prayerMethodsDao.selectAllPrayerMethod()

            withContext(Main){
                takeIf { method == null }?.apply {
                    mainRepository.saveMethodOfCalculationToDatabase(defaultMethod)
                    prayerMethod.value = defaultMethod
                }?.run {
                    prayerMethod.value = method.methodBased["prayerMethod"]
                }
            }
        }
    }

    fun getPrayerTimes(){
        mainRepository.getPrayersTimes(latlng.value!!, prayerMethod.value)
    }

    fun setLocationCoordination(latitude: Double, longitude: Double) {
        latlng.value = LatLng(latitude, longitude)
        toggleLoading(false)
    }

    fun setPrayerMethod(prayerMethod: String) {
        this.prayerMethod.value = prayerMethod
        CoroutineScope(IO).launch{
            val updatePrayerMethod = mutableMapOf(("prayerMethod" to prayerMethod))
            prayerMethodsDao.updatePrayerMethods(updatePrayerMethod)
        }
    }

    fun toggleLoading(loading: Boolean){
        isLoading.value = loading
    }

    companion object {
       const val defaultMethod =  "KARACHI_HANAF"
    }


}