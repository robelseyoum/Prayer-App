package com.robelseyoum3.perseusprayer.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PrayerMethods(val methodBased: MutableMap<String, String>) : Parcelable{
    operator fun get(s: String): Any {
        return methodBased
    }
}