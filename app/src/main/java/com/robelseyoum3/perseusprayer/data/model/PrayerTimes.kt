package com.robelseyoum3.perseusprayer.data.model

data class PrayerTimes (
    var mDate: MutableList<String>?,
    var mImsaak: String?,
    var mFajr: String?,
    var mSunrise: String?,
    var mZuhr: String?,
    var mAsr: String?,
    var mMaghrib: String?,
    var mISHA: String?
)
