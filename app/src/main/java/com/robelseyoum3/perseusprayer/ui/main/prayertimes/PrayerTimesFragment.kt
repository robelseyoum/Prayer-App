package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.robelseyoum3.perseusprayer.R

class PrayerTimesFragment : BasePrayerTimesFragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.prayertimes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "PrayerTimesFragment: ${viewModel.hashCode()}")
        subscribeLocationCoordinators()
        subscribePrayerTimes()
    }

    private fun subscribePrayerTimes() {

        mainViewModel.getPrayersTimes()

        mainViewModel.mDate.observe(this, Observer {
                Log.d(TAG, "PrayerTimesFragment: Date, Month, Year: $it")
        })
        mainViewModel.mImsaak.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: imsaak: $it")
        })

        mainViewModel.mFajr.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: Fajr: $it")
        })
        mainViewModel.mSunrise.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: sunrise: $it")
        })

        mainViewModel.mZuhr.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: Zuhr: $it")
        })

        mainViewModel.mAsr.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: Asr: $it")
        })

        mainViewModel.mMaghrib.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: Maghrib: $it")
        })

        mainViewModel.mISHA.observe(this, Observer {
            Log.d(TAG, "PrayerTimesFragment: ISHA: $it")
        })
    }

    private fun subscribeLocationCoordinators() {
        mainViewModel.coordinations.observe(this, Observer { coordinators ->
            Log.d(TAG, "PrayerTimesFragment: Latitude: ${coordinators["latitude"]}")
            Log.d(TAG, "PrayerTimesFragment: Longitude: ${coordinators["longitude"]}")
        })
    }

}