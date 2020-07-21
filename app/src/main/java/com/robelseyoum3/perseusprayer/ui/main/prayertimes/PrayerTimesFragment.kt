package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.utils.Resource
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
        Log.d(TAG, "PrayerTimesFragment: ${mainViewModel.hashCode()}")
        subscribeLocationCoordinators()
        subscribePrayerTimes()
    }

    private fun subscribePrayerTimes() {
        mainViewModel._prayer.observe(this, Observer { prayerData ->

            when(prayerData) {

                is Resource.Loading -> {  displayProgressbar() }

                is Resource.Success -> {setPrayerTimesFields(prayerData.data!!) }

                is Resource.Error -> { displayMessageContainer(prayerData.message)}

            }
        })

    }

    private fun setPrayerTimesFields(prayerTimes: PrayerTimes) {

            progress_bar_frg.visibility = View.GONE
            displayPrayTimes.visibility = View.VISIBLE
            llMessageContainer.visibility = View.GONE

            fajar_time.text = prayerTimes.mFajr
            sunrise_text.text = "SUNRISE at ${prayerTimes.mSunrise}"
            dhuhr_time.text = prayerTimes.mZuhr
            asar_time.text = prayerTimes.mAsr
            maghrib_time.text = prayerTimes.mMaghrib
            isha_time.text = prayerTimes.mISHA

            Log.d(TAG, "PrayerTimesFragment: Date, Month, Year: ${prayerTimes.mDate}")
            Log.d(TAG, "PrayerTimesFragment: imsaak: ${prayerTimes.mImsaak}")
            Log.d(TAG, "PrayerTimesFragment: Fajr: ${prayerTimes.mFajr}")
            Log.d(TAG, "PrayerTimesFragment: sunrise: ${prayerTimes.mSunrise}")
            Log.d(TAG, "PrayerTimesFragment: Zuhr: ${prayerTimes.mZuhr}")
            Log.d(TAG, "PrayerTimesFragment: Asr: ${prayerTimes.mAsr}")
            Log.d(TAG, "PrayerTimesFragment: Maghrib: ${prayerTimes.mMaghrib}")
            Log.d(TAG, "PrayerTimesFragment: ISHA: ${prayerTimes.mISHA}")
    }

    private fun displayProgressbar() {
        progress_bar_frg.visibility = View.VISIBLE
        displayPrayTimes.visibility = View.GONE
        llMessageContainer.visibility = View.GONE
    }

    private fun displayMessageContainer(message: String?) {

            llMessageContainer.visibility = View.VISIBLE
            displayPrayTimes.visibility = View.GONE
            progress_bar_frg.visibility = View.GONE
            tvMessage.text = message

    }

    private fun subscribeLocationCoordinators() {
        mainViewModel._coordination.observe(this, Observer { coordinators ->
            Log.d(TAG, "PrayerTimesFragment: Latitude: ${coordinators["latitude"]}")
            Log.d(TAG, "PrayerTimesFragment: Longitude: ${coordinators["longitude"]}")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.cancelActiveJobs()
    }

}