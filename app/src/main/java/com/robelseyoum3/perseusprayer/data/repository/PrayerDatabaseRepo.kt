package com.robelseyoum3.perseusprayer.data.repository

import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PrayerDatabaseRepo @Inject constructor(
    private val prayerTimesDao: PrayerTimesDao,
    private val prayerMethodsDao: PrayerMethodsDao
) : IPrayerDatabase{


    override fun savePrayerTime(prayerTimes: PrayerTimes) {
        CoroutineScope(Dispatchers.IO ).launch {
            prayerTimesDao.insertOnIgnore(prayerTimes)
        }
    }

    override fun savePrayerMethod(prayerMethod: String) {
        CoroutineScope(Dispatchers.IO).launch {
            PrayerMethods(mutableMapOf(("prayerMethod" to prayerMethod))).apply {
                prayerMethodsDao.insertOnIgnore(this)
            }
        }
    }

    override fun updatePrayerMethod(prayerMethod: String) {
        CoroutineScope(Dispatchers.IO).launch{
            mutableMapOf(("prayerMethod" to prayerMethod)).apply {
                prayerMethodsDao.updatePrayerMethods(this)
            }
        }
    }

    override suspend fun getPrayerMethod(): PrayerMethods {
        return prayerMethodsDao.selectAllPrayerMethod()
    }

}