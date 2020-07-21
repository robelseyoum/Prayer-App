package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.perseusprayer.ui.main.MainViewModel
import com.robelseyoum3.perseusprayer.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import java.lang.Exception
import javax.inject.Inject

abstract class BasePrayerTimesFragment : DaggerFragment() {

    val TAG: String = "AppDebug"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mainViewModel = activity?.run {
            ViewModelProvider(activity!!, providerFactory).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity")
    }


}