package com.robelseyoum3.perseusprayer.data.repository

import com.robelseyoum3.perseusprayer.concurrency.AppDispatchers
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PrayerDatabaseRepo @Inject constructor(
    private val prayerTimesDao: PrayerTimesDao,
    private val prayerMethodsDao: PrayerMethodsDao,
    private val appDispatchers: AppDispatchers
) : IPrayerDatabase, CoroutineScope {


    override fun savePrayerTime(prayerTimes: PrayerTimes) {
        launch {
            withContext(appDispatchers.io){
                prayerTimesDao.insertOnIgnore(prayerTimes)
            }
        }
    }

    override fun savePrayerMethod(prayerMethod: String) {
        launch {
            withContext(appDispatchers.io){
                PrayerMethods(mutableMapOf(( keyPrayerMethod to prayerMethod))).apply {
                    prayerMethodsDao.insertOnIgnore(this)
                }
            }
        }
    }

    override fun updatePrayerMethod(prayerMethod: String) {
        launch {
            withContext(appDispatchers.io){
                mutableMapOf(( keyPrayerMethod to prayerMethod)).apply {
                    prayerMethodsDao.updatePrayerMethods(this)
                }
            }
        }
    }

    override suspend fun getPrayerMethod(): PrayerMethods {
        return withContext(appDispatchers.io) {
            prayerMethodsDao.selectAllPrayerMethod()
        }
    }



    override val coroutineContext: CoroutineContext
        get() = appDispatchers.main

    companion object{
        const val keyPrayerMethod = "prayerMethod"
    }

}