package com.robelseyoum3.perseusprayer.ui.main.prayertimes


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.ui.main.MainViewModel
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
import com.robelseyoum3.perseusprayer.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment.*
import java.lang.Exception
import javax.inject.Inject

class PrayerMethodsDialog : DaggerDialogFragment() {

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

        mainViewModel = activity?.run { ViewModelProvider(activity!!, providerFactory).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity") as Throwable


        submit_button.setOnClickListener {
            val selectedId = myradiogroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedId)
            val selectedMethodValue = selectedRadioButton?.text?.toString()

            if (selectedMethodValue != null) {
                    setSelectedPrayerMethod(selectedMethodValue)
            } else{
                Log.d("selectedMethodValueNull","option is not selected")
            }

            dismiss()
        }

        cancel_button.setOnClickListener {
            dismiss()
        }
    }

    private fun setSelectedPrayerMethod(selectedMethodValue: String) {
        Log.d("setSelectedPrayerMethod", selectedMethodValue)
        when(selectedMethodValue) {
            EGYPT_SURVEY -> setPrayerMethodsValue(_EGYPT_SURVEY)
            FIXED_ISHAA -> setPrayerMethodsValue(_FIXED_ISHAA)
            KARACHI_HANAF -> setPrayerMethodsValue(_KARACHI_HANAF)
            MUSLIM_LEAGUE -> setPrayerMethodsValue(_MUSLIM_LEAGUE)
            NORTH_AMERICA -> setPrayerMethodsValue(_NORTH_AMERICA)
            UMM_ALQURRA -> setPrayerMethodsValue(_UMM_ALQURRA)
        }
    }

    private fun setPrayerMethodsValue(selectedMethodKey: String) {
        Log.d("setPrayerMethodsValue", selectedMethodKey)
        mainViewModel.setPrayerMethod(selectedMethodKey)
        mainViewModel.getPrayerTimes()
    }

}