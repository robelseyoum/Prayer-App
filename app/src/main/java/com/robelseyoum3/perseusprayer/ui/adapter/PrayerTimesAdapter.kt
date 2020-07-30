package com.robelseyoum3.perseusprayer.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerMethods
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.ui.adapter.viewholder.*
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.TYPE_ASARTIME
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.TYPE_DHUHRTIME
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.TYPE_FAJARTIME
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.TYPE_ISHAATIME
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.TYPE_MAGHRIBTIME
import com.robelseyoum3.perseusprayer.utils.Constants.Companion.TYPE_SUNRISETIME
import kotlin.properties.Delegates

class PrayerTimesAdapter constructor(val prayerTimes: MutableList<PrayerTimes>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ASARTIME -> AsarTiimeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.asar_time,
                    parent,
                    false
                )
            )
            TYPE_DHUHRTIME -> DhuhrTimeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.dhuhr_time,
                    parent,
                    false
                )
            )
            TYPE_FAJARTIME -> FajarTimeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.fajar_time,
                    parent,
                    false
                )
            )
            TYPE_ISHAATIME -> IshaaTimeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.ishaa_time,
                    parent,
                    false
                )
            )
            TYPE_MAGHRIBTIME -> IshaaTimeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.maghrib_time,
                    parent,
                    false
                )
            )
            else -> IshaaTimeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.sunrise_time,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = prayerTimes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ASARTIME -> (holder as AsarTiimeViewHolder).bind(prayerTimes[position])
            TYPE_DHUHRTIME -> (holder as DhuhrTimeViewHolder).bind(prayerTimes[position])
            TYPE_FAJARTIME -> (holder as FajarTimeViewHolder).bind(prayerTimes[position])
            TYPE_ISHAATIME -> (holder as IshaaTimeViewHolder).bind(prayerTimes[position])
            TYPE_MAGHRIBTIME -> (holder as MaghribTimeViewHolder).bind(prayerTimes[position])
            TYPE_SUNRISETIME -> (holder as SunriseTimeViewHolder).bind(prayerTimes[position])
        }
    }


    override fun getItemViewType(position: Int): Int {

        var intViewType: Int = 0
        when {
            TextUtils.isEmpty(prayerTimes[position].mAsr) -> {
                intViewType = TYPE_ASARTIME
            }
            TextUtils.isEmpty(prayerTimes[position].mZuhr) -> {
                intViewType =  TYPE_DHUHRTIME
            }
            TextUtils.isEmpty(prayerTimes[position].mFajr) -> {
                intViewType = TYPE_DHUHRTIME
            }
            TextUtils.isEmpty(prayerTimes[position].mZuhr) -> {
                intViewType =   TYPE_FAJARTIME
            }
            TextUtils.isEmpty(prayerTimes[position].mISHA) -> {
                intViewType =   TYPE_ISHAATIME
            }
            TextUtils.isEmpty(prayerTimes[position].mMaghrib) -> {
                intViewType =   TYPE_MAGHRIBTIME
            }
            TextUtils.isEmpty(prayerTimes[position].mSunrise) -> {
                intViewType =  TYPE_SUNRISETIME
            }
        }
        return intViewType
    }

}

//    override fun onBindViewHolder(holder: PrayerTimesViewHolder, position: Int) {
//
//        holder.fajarTime.text = prayerTimes[position].mFajr
//        holder.sunriseText.text = "SUNRISE at ${prayerTimes[position].mSunrise}"
//        holder.dhuhrTime.text = prayerTimes[position].mZuhr
//        holder.asarTime.text = prayerTimes[position].mAsr
//        holder.maghribTime.text = prayerTimes[position].mMaghrib
//        holder.ishaTime.text = prayerTimes[position].mISHA
//    }

