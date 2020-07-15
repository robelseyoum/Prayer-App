package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//        stateChangeListener.setLocationCoordination("3434", "34334")
//
//        stateChangeListener.getCoordination()

    }



}