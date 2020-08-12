package com.robelseyoum3.perseusprayer.utils

import com.azan.Method


fun checkPrayerBased(methodType: String?): Method {
    return when (methodType) {
        Constants._EGYPT_SURVEY -> Method.EGYPT_SURVEY
        Constants._FIXED_ISHAA -> Method.FIXED_ISHAA
        Constants._KARACHI_HANAF -> Method.KARACHI_HANAF
        Constants._MUSLIM_LEAGUE -> Method.MUSLIM_LEAGUE
        Constants._NORTH_AMERICA -> Method.NORTH_AMERICA
        Constants._UMM_ALQURRA -> Method.UMM_ALQURRA
        else -> Method.NONE
    }
}


fun checkPrayersBased(methodType: String?) : String {
    return when (methodType) {
        Constants._EGYPT_SURVEY -> Constants.EGYPT_SURVEY
        Constants._FIXED_ISHAA -> Constants.FIXED_ISHAA
        Constants._KARACHI_HANAF -> Constants.KARACHI_HANAF
        Constants._MUSLIM_LEAGUE -> Constants.MUSLIM_LEAGUE
        Constants._NORTH_AMERICA -> Constants.NORTH_AMERICA
        Constants._UMM_ALQURRA -> Constants.UMM_ALQURRA
        else -> "University of Islamic Sciences, Karachi (Hanafi)"
    }
}



val prayerNames = mapOf<Int, String>(
    0 to "Fajar",
    1 to "Sunrise",
    2 to "Zuhar",
    3 to "Asr",
    4 to "Magrib",
    5 to "Isha"
)
