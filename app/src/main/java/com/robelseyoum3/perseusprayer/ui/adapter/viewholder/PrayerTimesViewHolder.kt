package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prayer_times.view.*

class PrayerTimesViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val fajarTime: TextView = item.fajar_time
    val sunriseText: TextView = item.sunrise_text
    val dhuhrTime: TextView = item.dhuhr_time
    val asarTime: TextView = item.asar_time
    val maghribTime: TextView = item.maghrib_time
    val ishaTime: TextView = item.isha_time
//    val methodChange:TextView = item.change_based_text
//    val prayerBasedText:TextView = item.prayer_based_text


}
