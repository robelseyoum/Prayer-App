package com.robelseyoum3.perseusprayer.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.robelseyoum3.perseusprayer.TestDispatchers
import com.robelseyoum3.perseusprayer.data.model.*
import com.robelseyoum3.perseusprayer.data.repository.IPrayerDatabase
import com.robelseyoum3.perseusprayer.data.repository.IPrayerTime
import com.robelseyoum3.perseusprayer.data.repository.PrayerDatabaseRepo
import com.robelseyoum3.perseusprayer.data.repository.PrayerTimeRepo
import com.robelseyoum3.perseusprayer.utils.Resource
import com.robelseyoum3.perseusprayer.utils.prayerNames
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModel: MainViewModel
    private val prayerTimeRepo:IPrayerTime = mock<PrayerTimeRepo>()
    private val prayerDatabaseRepo: IPrayerDatabase = mock<PrayerDatabaseRepo>()


    @Before
    fun setUpTest(){
        MockKAnnotations.init(this)
        viewModel = MainViewModel(prayerTimeRepo, prayerDatabaseRepo, TestDispatchers)
    }

    @Test
    fun `Prayer method is null in the room db`(){
        runBlocking {
            whenever(
                prayerDatabaseRepo.getPrayerMethod()
            ).thenReturn(
                null
            )

            viewModel.initPrayerMethodModel()

            verify(prayerDatabaseRepo).savePrayerMethod(defaultMethod)
            assert(viewModel.prayerMethod.value == defaultMethod)
        }
    }

    @Test
    fun `Get prayer method from db, it should not null`(){

        runBlocking {
            whenever(
                prayerDatabaseRepo.getPrayerMethod()
            ).thenReturn(
                getFakePrayerMethod()
            )

            viewModel.initPrayerMethodModel()

            assert(viewModel.prayerMethod.value != defaultMethod)
        }
    }

    @Test
    fun `Set the value of latLng & toggle loading to false`(){
        viewModel.toggleLoading(false)

        assert(viewModel.isLoading.value != Resource.Loading(true))
    }

    @Test
    fun `Get prayer times from Azan lib successfully - value should not be null`(){
        viewModel.latLng.value = LatLng(0.0,0.0)
        viewModel.prayerMethod.value = defaultMethod

        whenever(
            prayerTimeRepo.getPrayersTimes(
                viewModel.latLng.value!!,
                viewModel.prayerMethod.value
            )
        ).thenReturn(
            getFakePrayerTimes()
        )

        viewModel.getPrayerTimes()


        assert(viewModel.azanTime.value!=null)
    }

    @Test
    fun `Get prayer times from Azan lib successfully - value should be null`(){
        viewModel.latLng.value = LatLng(0.0,0.0)
        viewModel.prayerMethod.value = defaultMethod

        whenever(
            prayerTimeRepo.getPrayersTimes(
                viewModel.latLng.value!!,
                viewModel.prayerMethod.value
            )
        ).thenReturn(
            null
        )

        viewModel.getPrayerTimes()


        assert(viewModel.azanTime.value == null)
    }


    private fun getFakePrayerMethod(): PrayerMethods{
        val prayerMethods = mutableMapOf( keyPrayerMethod to nonDefaultMethod)
        return PrayerMethods(prayerMethods)
    }

    private fun getFakePrayerTimes(): PrayerTimes {
        val dateTime = mutableListOf(
            DateMonthYear(
                "13",
                "08",
                "2020"
            )
        )

        val azanTime = mutableListOf<AzanTime>()

        for (i in 0..5) {
            if (i == 1) {
                azanTime.add(
                    AzanTime(
                        prayerNames[i] ?: error(""),
                        "something",
                        1
                    )
                )
            } else {
                azanTime.add(
                    AzanTime(
                        prayerNames[i] ?: error(""),
                        "something",
                        0
                    )
                )
            }
        }

        return PrayerTimes(
            dateTime,
            azanTime
        )
    }

    companion object{
        const val keyPrayerMethod = "prayerMethod"
        const val defaultMethod = "KARACHI_HANAF"
        const val nonDefaultMethod = "FIXED_ISHAA"

    }

}