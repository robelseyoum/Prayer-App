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

    /*

    val asarTimeline: ImageView = item.circle_line_timeline_asar

    fun bind(prayerTimes: PrayerTimes) {
        itemView.asar_time.text = prayerTimes.mAsr.toString()
        //asarTimeline.setImageDrawable(Uri.parse("android.resource://"+ BuildConfig.APPLICATION_ID R.drawable.pointer)
        Log.d("AsarTiimeViewHolder", "$prayerTimes.mAsr")
    }
     */

}
