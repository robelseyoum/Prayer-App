package com.robelseyoum3.perseusprayer.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.repository.MainRepository
import com.robelseyoum3.perseusprayer.utils.PreferenceKeys
import com.robelseyoum3.perseusprayer.utils.Resource
import javax.inject.Inject

class MainViewModel  @Inject constructor(val mainRepository: MainRepository, val sharedPreferences: SharedPreferences) : ViewModel()  {

    var _coordination: MutableLiveData<MutableMap<String, Double>> = MutableLiveData()

   val sharedValue = sharedPreferences.getString(PreferenceKeys.METHOD_CALCULATION, null)

//    Log.d("getPrayersTimes", sharedValue)



    val _prayer: LiveData<Resource<PrayerTimes>> = Transformations
        .switchMap(_coordination){
            mainRepository.getPrayersTimes(it, sharedValue)
        }

    fun setLocationCoordination(latitude: String, longitude: String) {
        val update_lat_long = mutableMapOf(("latitude" to latitude.toDouble()),("longitude" to longitude.toDouble()))
        if(_coordination.value == update_lat_long){
            return
        }
        _coordination.value = update_lat_long
    }


    fun cancelActiveJobs(){
        mainRepository.cancelJobs()
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }


}