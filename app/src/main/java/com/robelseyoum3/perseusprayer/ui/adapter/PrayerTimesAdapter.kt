package com.robelseyoum3.perseusprayer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azan.Method
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.ui.adapter.listener.PrayerBasedListener
import com.robelseyoum3.perseusprayer.ui.adapter.viewholder.PrayerTimesViewHolder

class PrayerTimesAdapter constructor(val prayerTimes: MutableList<PrayerTimes>, private val prayerBasedListener: PrayerBasedListener)
    : RecyclerView.Adapter<PrayerTimesViewHolder>() {

    val LOCATION_BASED_PRAYER  =
        mutableMapOf(
            ("EGYPT_SURVEY" to "Egyptian General Authority of Survey" ),
            ("FIXED_ISHAA" to "Fixed Ishaa Angle Interval"),
            ("KARACHI_HANAF" to "University of Islamic Sciences, Karachi (Hanafi)"),
            ("MUSLIM_LEAGUE" to "Egyptian General Authority of Survey" ),
            ("NORTH_AMERICA" to "Islamic Society of North America"),
            ("UMM_ALQURRA" to "Om Al-Qurra University" )
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerTimesViewHolder  =
        PrayerTimesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.prayer_times, parent, false))

    override fun getItemCount(): Int = prayerTimes.size

    override fun onBindViewHolder(holder: PrayerTimesViewHolder, position: Int) {
        holder.fajarTime.text = prayerTimes[position].mFajr
        holder.sunriseText.text = "SUNRISE at ${prayerTimes[position].mSunrise}"
        holder.dhuhrTime.text =  prayerTimes[position].mZuhr
        holder.asarTime.text = prayerTimes[position].mAsr
        holder.maghribTime.text = prayerTimes[position].mMaghrib
        holder.ishaTime.text = prayerTimes[position].mISHA
        holder.prayerBasedText.text = LOCATION_BASED_PRAYER["FIXED_ISHAA"].toString()

        holder.methodChange.setOnClickListener {
//            mutableMapOf(("latitude" to latitude.toDouble()),("longitude" to longitude.toDouble()))
            prayerBasedListener.onClick(LOCATION_BASED_PRAYER)
        }

    }


}