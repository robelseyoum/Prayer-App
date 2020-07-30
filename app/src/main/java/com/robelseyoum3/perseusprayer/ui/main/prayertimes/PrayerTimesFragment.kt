package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azan.Method
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.ui.adapter.PrayerTimesAdapter
import com.robelseyoum3.perseusprayer.utils.Constants
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.EGYPT_SURVEY
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.FIXED_ISHAA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.KARACHI_HANAF
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.MUSLIM_LEAGUE
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.NORTH_AMERICA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.UMM_ALQURRA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._EGYPT_SURVEY
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._FIXED_ISHAA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._KARACHI_HANAF
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._MUSLIM_LEAGUE
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._NORTH_AMERICA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion._UMM_ALQURRA
import com.robelseyoum3.perseusprayer.utils.PreferenceKeys
import com.robelseyoum3.perseusprayer.utils.Resource
import kotlinx.android.synthetic.main.prayertimes_fragment.*
import javax.inject.Inject

class PrayerTimesFragment : BasePrayerTimesFragment() {

    lateinit var prayerTimesAdapter: PrayerTimesAdapter
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

        setClickListenerPrayerMethods()
        setupRecyclerView()
        observePrayerTimes()

    }

    private fun observePrayerTimes() {
        mainViewModel._loading.observe(this, Observer { isLoading ->
            if(!isLoading){
                mainViewModel.getPrayerTimes()
                subscribeObserver()
            }
        })
    }

    private fun setClickListenerPrayerMethods() {
        change_prayer_based_text.setOnClickListener {
            findNavController().navigate(R.id.prayerMethodsDialog)
        }
    }


    private fun setupRecyclerView() {
        rvTimes.layoutManager = LinearLayoutManager(view?.context)
        prayerTimesAdapter = PrayerTimesAdapter(mutableListOf())
        rvTimes.adapter = prayerTimesAdapter
    }

    private fun subscribeObserver() {

        mainViewModel._prayer.observe(this, Observer { prayerData ->

            when(prayerData) {

                is Resource.Loading -> {  displayProgressbar() }

                is Resource.Success -> {setPrayerTimesFields(prayerData.data!!) }

                is Resource.Error -> { displayMessageContainer(prayerData.message)}

            }
        })

        mainViewModel._prayerMethod.observe(this, Observer {
            it?.let {
                change_prayer_based_text.text = "Based on : ${checkPrayerBased(it)}"
                mainViewModel.getPrayerTimes()
            }
        })

    }

    private fun checkPrayerBased(methodType: String?) : String {
        return when (methodType) {
            _EGYPT_SURVEY -> EGYPT_SURVEY
            _FIXED_ISHAA -> FIXED_ISHAA
            _KARACHI_HANAF -> KARACHI_HANAF
            _MUSLIM_LEAGUE -> MUSLIM_LEAGUE
            _NORTH_AMERICA -> NORTH_AMERICA
            _UMM_ALQURRA -> UMM_ALQURRA
            else -> "University of Islamic Sciences, Karachi (Hanafi)"
        }
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


    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.cancelActiveJobs()
    }


}