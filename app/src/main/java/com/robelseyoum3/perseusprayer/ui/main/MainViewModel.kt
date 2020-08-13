package com.robelseyoum3.perseusprayer.ui.main

import androidx.lifecycle.MutableLiveData
import com.robelseyoum3.perseusprayer.concurrency.AppDispatchers
import com.robelseyoum3.perseusprayer.data.model.LatLng
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.repository.IPrayerDatabase
import com.robelseyoum3.perseusprayer.data.repository.IPrayerTime
import com.robelseyoum3.perseusprayer.ui.BaseViewModel
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor(private val prayerTimeRepo: IPrayerTime,
                                         private val prayerDatabaseRepo: IPrayerDatabase,
                                         appDispatchers: AppDispatchers) : BaseViewModel(appDispatchers)  {

    var latLng: MutableLiveData<LatLng> = MutableLiveData()
    var prayerMethod: MutableLiveData<String> = MutableLiveData()
    var isLoading: MutableLiveData<Resource<Boolean>> = MutableLiveData()

    val azanTime: MutableLiveData<PrayerTimes> = MutableLiveData()

    fun initPrayerMethodModel(){

        launch {
            val method = prayerDatabaseRepo.getPrayerMethod()

            if(method != null){
                prayerMethod.value = method.methodBased["prayerMethod"]
            } else {
                prayerDatabaseRepo.savePrayerMethod(defaultMethod)
                prayerMethod.value = defaultMethod
            }
        }

    }

    fun getPrayerTimes(){
        val prayerTimes = prayerTimeRepo.getPrayersTimes(latLng.value!!, prayerMethod.value)
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

    fun setLatlng(latitude: Double, longitude: Double) {
        latLng.value = LatLng(latitude, longitude)
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