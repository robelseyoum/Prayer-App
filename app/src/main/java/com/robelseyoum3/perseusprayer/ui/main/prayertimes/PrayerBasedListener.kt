package com.robelseyoum3.perseusprayer.ui.main.prayertimes

import com.robelseyoum3.perseusprayer.data.model.PrayerMethods

interface PrayerBasedListener {
    fun onClick(prayerMethods: PrayerMethods)
//    fun onClick(methodType: String)
}