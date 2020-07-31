package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import kotlinx.android.synthetic.main.ishaa_time.view.*


class IshaaTimeViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val ishaTime: TextView = item.isha_time
    val ishaTimeLine: ImageView = item.circle_line_timeline_isha

    fun bind(prayerTimes: PrayerTimes){
        itemView.isha_time.text = prayerTimes.mISHA
    }

}