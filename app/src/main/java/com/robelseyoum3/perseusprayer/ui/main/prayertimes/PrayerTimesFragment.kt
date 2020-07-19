package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.robelseyoum3.perseusprayer.R
import kotlinx.android.synthetic.main.prayertimes_fragment.*

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
        mainViewModel.prayerTimeMutableLiveData.observe(this, Observer { prayertimes ->
//            stateChangeListener.onDataStateChange(prayertimes)


//            fajr.text = prayertimes.mFajr
//            sunrise.text = prayertimes.mSunrise
//            dhuhr.text = prayertimes.mZuhr
//            asr.text = prayertimes.mAsr
//            maghrib.text = prayertimes.mMaghrib
//            sunset.text = prayertimes.mSunrise
//            isha.text = prayertimes.mISHA
//
//            current_date.text = prayertimes.mDate.toString()

            Log.d(TAG, "PrayerTimesFragment: Date, Month, Year: ${prayertimes.mDate}")
            Log.d(TAG, "PrayerTimesFragment: imsaak: ${prayertimes.mImsaak}")
            Log.d(TAG, "PrayerTimesFragment: Fajr: ${prayertimes.mFajr}")
            Log.d(TAG, "PrayerTimesFragment: sunrise: ${prayertimes.mSunrise}")
            Log.d(TAG, "PrayerTimesFragment: Zuhr: ${prayertimes.mZuhr}")
            Log.d(TAG, "PrayerTimesFragment: Asr: ${prayertimes.mAsr}")
            Log.d(TAG, "PrayerTimesFragment: Maghrib: ${prayertimes.mMaghrib}")
            Log.d(TAG, "PrayerTimesFragment: ISHA: ${prayertimes.mISHA}")

        })
    }

    private fun subscribeLocationCoordinators() {
        mainViewModel.coordinations.observe(this, Observer { coordinators ->
            Log.d(TAG, "PrayerTimesFragment: Latitude: ${coordinators["latitude"]}")
            Log.d(TAG, "PrayerTimesFragment: Longitude: ${coordinators["longitude"]}")
        })
    }

}