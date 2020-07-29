package com.robelseyoum3.perseusprayer.ui.main.qibla

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.robelseyoum3.perseusprayer.R

class QiblaFragment : BaseQiblaFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.qibla_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "QiblaFragment: ${mainViewModel.hashCode()}")
        subscribeLocationCoordinators()
    }

    private fun subscribeLocationCoordinators() {
//        mainViewModel._coordination.observe(this, Observer { coordinators ->
//            Log.d(TAG, "QiblaFragment: Latitude: ${coordinators["latitude"]}")
//            Log.d(TAG, "QiblaFragment: Longitude: ${coordinators["longitude"]}")
//        })
    }

}