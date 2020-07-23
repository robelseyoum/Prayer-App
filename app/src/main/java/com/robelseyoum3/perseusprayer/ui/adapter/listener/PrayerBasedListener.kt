package com.robelseyoum3.perseusprayer.ui.adapter.listener

import com.azan.Method
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods

interface PrayerBasedListener {
    fun onClick(prayerMethods: PrayerMethods)
//    fun onClick(methodType: String)
}