package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.ui.main.MainViewModel
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.EGYPT_SURVEY
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.FIXED_ISHAA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.KARACHI_HANAF
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.MUSLIM_LEAGUE
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.NORTH_AMERICA
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.UMM_ALQURRA
import com.robelseyoum3.perseusprayer.utils.PreferenceKeys
import com.robelseyoum3.perseusprayer.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment.*
import java.lang.Exception
import javax.inject.Inject

class PrayerMethodsDialog : DaggerDialogFragment() {

    private lateinit var prayerMethods: PrayerMethods

    @Inject
    lateinit var sharedPrefsEditor: SharedPreferences.Editor

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prayerMethods  = arguments!!.getParcelable<PrayerMethods>("calc_method") as PrayerMethods

        mainViewModel = activity?.run { ViewModelProvider(activity!!, providerFactory).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity")


        displayPrayMethodName(prayerMethods)

        submit_button.setOnClickListener {
            val selectedId = myradiogroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedId)
            val selectedMethodValue = selectedRadioButton.text.toString()

            storeSelectedMethodValueToShared(selectedMethodValue)

//            val prayerBaseLoc: String? = sharedPreferences.getString(PreferenceKeys.METHOD_CALCULATION, null)
//            Log.d("ValueFromSharedClick_", prayerBaseLoc)
            dismiss()
        }

        cancel_button.setOnClickListener {
            dismiss()
        }
    }

    private fun storeSelectedMethodValueToShared(selectedMethodValue: String) {
        Log.d("MethodValueToShared", selectedMethodValue)
        when(selectedMethodValue) {
            EGYPT_SURVEY -> storeValue("EGYPT_SURVEY")
            FIXED_ISHAA -> storeValue("FIXED_ISHAA")
            KARACHI_HANAF -> storeValue("KARACHI_HANAF")
            MUSLIM_LEAGUE -> storeValue("MUSLIM_LEAGUE")
            NORTH_AMERICA -> storeValue("NORTH_AMERICA")
            UMM_ALQURRA -> storeValue("UMM_ALQURRA")
        }
    }

    private fun storeValue(selectedMethodKey: String) {
        Log.d("storeValue", selectedMethodKey)
        sharedPrefsEditor.putString(PreferenceKeys.METHOD_CALCULATION, selectedMethodKey)
        sharedPrefsEditor.apply()
    }

    private fun displayPrayMethodName(prayerMethods: PrayerMethods?) {

        radio_egypt_survey.text = prayerMethods!!.methodBased["EGYPT_SURVEY"]
        Log.d("displayPrayMethodName", " ${prayerMethods.methodBased["NORTH_AMERICA"]}")

        radio_fixed_ishaa.text = prayerMethods.methodBased["FIXED_ISHAA"]
        radio_karachi_hanaf.text = prayerMethods.methodBased["KARACHI_HANAF"]
        radio_muslim_league.text = prayerMethods.methodBased["MUSLIM_LEAGUE"]
        radio_north_america.text = prayerMethods.methodBased["NORTH_AMERICA"]
        radio_umm_alqurra.text = prayerMethods.methodBased["UMM_ALQURRA"]
    }


}