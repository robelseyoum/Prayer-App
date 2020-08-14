package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.ui.adapter.PrayerTimesAdapter
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
import com.robelseyoum3.perseusprayer.utils.Resource
import com.robelseyoum3.perseusprayer.utils.checkPrayerBased
import com.robelseyoum3.perseusprayer.utils.checkPrayersBased
import kotlinx.android.synthetic.main.prayertimes_fragment.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import javax.inject.Inject

class PrayerTimesFragment : BasePrayerTimesFragment() {

    lateinit var prayerTimesAdapter: PrayerTimesAdapter

    val dayMonth: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.prayertimes_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "PrayerTimesFragment: ${mainViewModel.hashCode()}")

        setClickListenerPrayerMethods()
        setupRecyclerView()
        observePrayerTimes()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observePrayerTimes() {
        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if(!isLoading.data!!){
                mainViewModel.getPrayerTimes()
                mainViewModel.addPrayerTimes()
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
        prayerTimesAdapter = PrayerTimesAdapter()
        rvTimes.adapter = prayerTimesAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun subscribeObserver() {

        mainViewModel.azanTime.observe(this, Observer { prayerTimes ->
            setPrayerTimesFields(prayerTimes)
            currentDateTime(prayerTimes)
        })

        mainViewModel.prayerMethod.observe(this, Observer {
            it?.let {
                change_prayer_based_text.text = "Based on : ${checkPrayersBased(it)}"
                mainViewModel.getPrayerTimes()
            }
        })

    }

    private fun setPrayerTimesFields(prayerTimes: PrayerTimes) {
            prayerTimesAdapter.data = prayerTimes.azanTimes
            progress_bar_frg.visibility = View.INVISIBLE
            rvTimes.visibility = View.VISIBLE
            llMessageContainer.visibility = View.INVISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun currentDateTime(prayerTimes: PrayerTimes){
        val mTime = LocalDateTime.now()
        current_time.text = mTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
        current_date.text = mTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
    }


}