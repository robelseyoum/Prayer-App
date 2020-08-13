package com.robelseyoum3.perseusprayer.ui

import android.util.Log
import com.robelseyoum3.perseusprayer.utils.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class BaseActivity : DaggerAppCompatActivity() {

    val TAG: String = "AppDebug"

    abstract fun setLatlng(latitude: Double, longitude: Double) //get the the coordinate

}