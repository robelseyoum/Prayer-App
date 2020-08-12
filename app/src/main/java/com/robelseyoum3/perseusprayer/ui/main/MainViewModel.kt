package com.robelseyoum3.perseusprayer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robelseyoum3.perseusprayer.data.model.LatLng
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.repository.IPrayerDatabase
import com.robelseyoum3.perseusprayer.data.repository.IPrayerTime
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel  @Inject constructor(private val prayerTimeRepo: IPrayerTime, private val prayerDatabaseRepo: IPrayerDatabase) : ViewModel()  {

    var latlng: MutableLiveData<LatLng> = MutableLiveData()
    var prayerMethod: MutableLiveData<String> = MutableLiveData()
    var isLoading: MutableLiveData<Resource<Boolean>> = MutableLiveData()

    val azanTime: MutableLiveData<PrayerTimes> = MutableLiveData()

    fun initPrayerMethodModel(){
        CoroutineScope(IO).launch {
            val method = prayerDatabaseRepo.getPrayerMethod()

            withContext(Main){
                takeIf { method == null }?.apply {
                    prayerDatabaseRepo.savePrayerMethod(defaultMethod)
                    prayerMethod.value = defaultMethod
                }?.run {
                    prayerMethod.value = method.methodBased["prayerMethod"]
                }
            }
        }
    }

    fun getPrayerTimes(){
        val prayerTimes = prayerTimeRepo.getPrayersTimes(latlng.value!!, prayerMethod.value)
        azanTime.value = prayerTimes
    }

    fun updatePrayerMethods(prayerMethod: String){
        prayerDatabaseRepo.updatePrayerMethod(prayerMethod)
    }

    fun addPrayerTimes(){
        azanTime.value?.let {
            prayerDatabaseRepo.savePrayerTime(it)
        }
    }

    fun setLocationCoordination(latitude: Double, longitude: Double) {
        latlng.value = LatLng(latitude, longitude)
        toggleLoading(false)
    }

    fun setPrayerMethod(prayerMethod: String) {
        this.prayerMethod.value = prayerMethod
    }

    fun toggleLoading(loading: Boolean){
        isLoading.value = Resource.Loading(loading)
    }

    companion object {
        const val defaultMethod =  "KARACHI_HANAF"
    }


}