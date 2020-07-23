package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azan.Method
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.ui.adapter.PrayerTimesAdapter
import com.robelseyoum3.perseusprayer.ui.adapter.listener.PrayerBasedListener
import com.robelseyoum3.perseusprayer.utils.PreferenceKeys
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.android.synthetic.main.prayertimes_fragment.*
import javax.inject.Inject

class PrayerTimesFragment : BasePrayerTimesFragment() {

    lateinit var prayerTimesAdapter: PrayerTimesAdapter
    lateinit var prayerMethods: PrayerMethods

    @Inject
    lateinit var sharedPrefsEditor: SharedPreferences.Editor

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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
        subscribePrayerMethods()
        setupRecyclerView()
        subscribeLocationCoordinators()
        subscribePrayerTimes()

        val sharedValue = sharedPreferences.getString(PreferenceKeys.METHOD_CALCULATION, "Roba_Shared")
        //FIXED_ISHAA
        Log.d("getPrayersTimes_pf", sharedValue)
    }

    private fun subscribePrayerMethods() {
        prayerMethods = PrayerMethods(
            mutableMapOf(
                ("EGYPT_SURVEY" to "Egyptian General Authority of Survey" ),
                ("FIXED_ISHAA" to "Fixed Ishaa Angle Interval"),
                ("KARACHI_HANAF" to "University of Islamic Sciences, Karachi (Hanafi)"),
                ("MUSLIM_LEAGUE" to "Egyptian General Authority of Survey" ),
                ("NORTH_AMERICA" to "Islamic Society of North America"),
                ("UMM_ALQURRA" to "Om Al-Qurra University" )
            )
        )
    }

    private val prayerBasedListener: PrayerBasedListener = object : PrayerBasedListener{

        override fun onClick(prayerMethods: PrayerMethods) {
            val prayerMethodsDialog = PrayerMethodsDialog()
            val arguments = Bundle()
            arguments.putParcelable("calc_method", prayerMethods)
            prayerMethodsDialog.arguments = arguments
            findNavController().navigate(R.id.prayerMethodsDialog, arguments)
        }
    }

    private fun setupRecyclerView() {
        rvTimes.layoutManager = LinearLayoutManager(view?.context)
        prayerTimesAdapter = PrayerTimesAdapter(mutableListOf(), prayerMethods, prayerBasedListener)
        rvTimes.adapter = prayerTimesAdapter
    }

    private fun subscribePrayerTimes() {
        Log.d(TAG, "PrayerTimesFragment: $prayerMethods")

        mainViewModel._prayer.observe(this, Observer { prayerData ->

            when(prayerData) {

                is Resource.Loading -> {  displayProgressbar() }

                is Resource.Success -> {setPrayerTimesFields(prayerData.data!!) }

                is Resource.Error -> { displayMessageContainer(prayerData.message)}

            }
        })

    }

    private fun setPrayerTimesFields(prayerTimes: PrayerTimes) {
            prayerTimesAdapter.prayerTimes.clear()
            prayerTimesAdapter.prayerTimes.addAll(mutableListOf(prayerTimes))
            prayerTimesAdapter.notifyDataSetChanged()

            progress_bar_frg.visibility = View.GONE
            rvTimes.visibility = View.VISIBLE
            llMessageContainer.visibility = View.GONE
    }

    private fun displayProgressbar() {
        progress_bar_frg.visibility = View.VISIBLE
        rvTimes.visibility = View.GONE
        llMessageContainer.visibility = View.GONE
    }

    private fun displayMessageContainer(message: String?) {
            llMessageContainer.visibility = View.VISIBLE
            rvTimes.visibility = View.GONE
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