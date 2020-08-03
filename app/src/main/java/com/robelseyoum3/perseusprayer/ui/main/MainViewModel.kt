package com.robelseyoum3.perseusprayer.ui.main

import androidx.lifecycle.*
import com.robelseyoum3.perseusprayer.data.model.LatLng
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.repository.MainRepository
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val prayerMethodsDao: PrayerMethodsDao
) : ViewModel() {

    var latLng: MutableLiveData<LatLng> = MutableLiveData()
    var prayerMethod: MutableLiveData<String> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun initPrayerMethodModel() {
        CoroutineScope(IO).launch {
            val method = prayerMethodsDao.selectAllPrayerMethod()

            withContext(Main) {
                takeIf { method == null }?.apply {
                    mainRepository.savePrayerMethod(defaultMethod)
                    prayerMethod.value = defaultMethod
                }?.run {
                    prayerMethod.value = method.methodBased["prayerMethod"]
                }
            }
        }
    }

    val azanTimes: LiveData<Resource<PrayerTimes>> = mainRepository.repo

    fun getPrayerTimes() {
        mainRepository.getPrayersTimes(latLng.value!!, prayerMethod.value)
    }

    fun setLocationCoordination(latitude: Double, longitude: Double) {
        latLng.value = LatLng(latitude, longitude)
        toggleLoading(false)
    }

    fun setPrayerMethod(prayerMethod: String) {
        this.prayerMethod.value = prayerMethod
    }

    fun toggleLoading(loading: Boolean) {
        isLoading.value = loading
    }

    companion object {
        const val defaultMethod = "KARACHI_HANAF"
    }
}