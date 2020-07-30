package com.robelseyoum3.perseusprayer.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import kotlinx.android.synthetic.main.fajar_time.view.*
import kotlinx.android.synthetic.main.fajar_time.view.circle_line_timeline_fajar
import kotlinx.android.synthetic.main.fajar_time.view.fajar_time
import kotlinx.android.synthetic.main.prayer_times.view.*
import kotlinx.android.synthetic.main.prayer_times.view.fajar

class FajarTimeViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val fajarTimeline: ImageView = item.circle_line_timeline_fajar

    fun bind(prayerTimes: PrayerTimes){
        itemView.fajar_time.text = prayerTimes.mFajr
    }

}