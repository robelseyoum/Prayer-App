package com.robelseyoum3.perseusprayer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.robelseyoum3.perseusprayer.data.model.*
import com.robelseyoum3.perseusprayer.data.persistence.PrayerMethodsDao
import com.robelseyoum3.perseusprayer.data.persistence.PrayerTimesDao
import com.robelseyoum3.perseusprayer.data.repository.PrayerTimeRepo
import com.robelseyoum3.perseusprayer.ui.main.MainViewModel
import com.robelseyoum3.perseusprayer.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule : TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var prayerTimeRepo: PrayerTimeRepo

    lateinit var mainViewModel: MainViewModel

    /*  ------------------------------------------------------- */

    @MockK
    lateinit var prayerMethodsDao: PrayerMethodsDao

    @MockK
    lateinit var prayerTimesDao: PrayerTimesDao

    @MockK
    lateinit var prayerTimes: PrayerTimes

    @MockK
    lateinit var prayerMethods: PrayerMethods

    @MockK
    private lateinit var prayerTimesObserver: Observer<Resource<PrayerTimes>>

    @MockK
    private lateinit var prayerMethodObserver: Observer<Resource<PrayerMethods>>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(prayerTimeRepo, prayerMethodsDao)

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

        val times = PrayerTimes(dateTime, azanTime)

        val coordination = LatLng(Latitude, Longitude)

    /**
     getPrayertime success
     */
    @Test
    fun getPrayerTime_successPrayerTimes(){

//        mainViewModel.getPrayerTimes()
//
//        mainRepository.getPrayersTimes(coordination, defaultPrayerMethods)
//
//        assertEquals(mainRepository.repo.getOrAwaitValue(), Resource.Success(times))
//
//        assertEquals(mainViewModel.azanTime.getOrAwaitValue(), Resource.Success(times))

    }

}