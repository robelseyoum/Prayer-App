package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import kotlinx.android.synthetic.main.dhuhr_time.view.*


class DhuhrTimeViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val dhuhrTime: TextView = item.dhuhr_time
    val dhuhrTimeLine: ImageView = item.circle_line_timeline_dhuhr

    fun bind(prayerTimes: PrayerTimes){
        itemView.dhuhr_time.text = prayerTimes.mAsr
    }
}