package com.robelseyoum3.perseusprayer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.robelseyoum3.perseusprayer.data.model.*
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import com.robelseyoum3.perseusprayer.data.repository.MainRepository
import com.robelseyoum3.perseusprayer.ui.main.MainViewModel
import com.robelseyoum3.perseusprayer.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    //Run task synchronously
    @Rule
    @JvmField
    val instantExecutorRule : TestRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel


    @Mock
    lateinit var mainRepository: MainRepository

    /*  ------------------------------------------------------- */

    @Mock
    lateinit var prayerMethodsDao: PrayerMethodsDao

    @Mock
    lateinit var prayerTimesDao: PrayerTimesDao

    @Mock
    lateinit var prayerTimes: PrayerTimes

    @Mock
    lateinit var prayerMethods: PrayerMethods

    @Mock
    private lateinit var prayerTimesObserver: Observer<Resource<PrayerTimes>>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    /* Copyright 2019 Google LLC.
   SPDX-License-Identifier: Apache-2.0 */
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

/////////
        val Longitude: Double = -0.118092
        val Latitude: Double= 51.509865

        val defaultPrayerMethods =  "KARACHI_HANAF"

        val azanTime = mutableListOf(
            AzanTime("Fajar", "12:03:34", 0),
            AzanTime("Sunrise", "12:03:34", 1),
            AzanTime("Zuhar", "12:03:34", 0),
            AzanTime("Asr", "12:03:34", 0),
            AzanTime("Magrib", "12:03:34", 0),
            AzanTime("Isha", "12:03:34", 0)
        )

        val dateTime = mutableListOf(
            DateTimes(
                "02",
                "08",
                "2020"
            )
        )

        val times = PrayerTimes(
            dateTime,
            azanTime
        )
    /**
     getPrayertime success
     */
    @Test
    fun getPrayerTime_successPrayerTimes(){

        val coordination = LatLng(Latitude, Longitude)

        every { mainRepository.getPrayersTimes(coordination, defaultPrayerMethods) }

        mainViewModel.getPrayerTimes()


    }

    
    /**
    getPrayertime fail
     */

//    @Test
//    fun getPrayerTime_errorPrayerTimes(){
//
//    }

    /////////

    /**
    addPrayerTimes to cache success
     */

//    @Test
//    fun insertPrayerTimes_returnRow(){
//
//    }

    /**
    addPrayerTimes to cache fail
     */
//    @Test
//    fun insertPrayerTimes_returnFailure(){
//
//    }

    /////////
    /**
    updatePrayerMethods success
     */
//    @Test
//    fun updatePrayerMethods_returnPrayerMethodUpdated(){
//
//    }


    /**
    updatePrayerMethods fail
     */

//    @Test
//    fun updatePrayerMethods_returnFailure(){
//
//    }

/////////
    /**
    setLocationCoordination success
    */

//    @Test
//    fun setLocationCoordination_successCoordinator(){
//
//    }

    /**
    setLocationCoordination fail
     */

//    @Test
//    fun setLocationCoordination_errorCoordinator(){
//
//    }

/////////
    /**
    setPrayerMethod success
     */

//    @Test
//    fun setPrayerMethod_prayerMethodSuccess(){
//
//    }

    /**
    setPrayerMethod fail
     */

//    @Test
//    fun setPrayerMethod_prayerMethodError(){
//
//    }

/////////
    /**
    toggleLoading success
     */
//    @Test
//    fun toggleLoading_successToggling(){
//
//    }

    /**
    toggleLoading fail
     */
//    @Test
//    fun toggleLoading_errorToggling(){
//
//    }


}