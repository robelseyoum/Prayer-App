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
import java.util.*
import javax.inject.Inject

class PrayerTimesFragment : BasePrayerTimesFragment() {

    lateinit var prayerTimesAdapter: PrayerTimesAdapter
    @Inject
    lateinit var sharedPrefsEditor: SharedPreferences.Editor

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    val dayMonth: String? = null

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
//            val prayerMethodsDialog = PrayerMethodsDialog()
//            val argument = Bundle()
//            argument.putParcelable("calc_method", mainViewModel._prayerMethod.value)
//            prayerMethodsDialog.arguments = arguments
            findNavController().navigate(R.id.prayerMethodsDialog)
        }
    }

    private fun setupRecyclerView() {
        rvTimes.layoutManager = LinearLayoutManager(view?.context)
        prayerTimesAdapter = PrayerTimesAdapter()
        rvTimes.adapter = prayerTimesAdapter
    }

    private fun subscribeObserver() {

        mainViewModel.azanTime.observe(this, Observer { prayerTimes ->
            setPrayerTimesFields(prayerTimes)
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

    private fun currentDateTime() {
        val date = Calendar.getInstance().time
        current_date.text = ""
        current_time.text = ""
    }

    private fun displayMessageContainer(message: String?) {
            llMessageContainer.visibility = View.VISIBLE
            rvTimes.visibility = View.INVISIBLE
            progress_bar_frg.visibility = View.INVISIBLE
            tvMessage.text = message
    }

}