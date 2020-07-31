package com.robelseyoum3.perseusprayer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.data.model.PrayerTimes
import com.robelseyoum3.perseusprayer.ui.components.TimelineView
import kotlinx.android.synthetic.main.item_prayer_time.view.*

class PrayerTimesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<PrayerTimes>()

    var data: List<PrayerTimes>
        get() = list.toList()
        set(value) {
            list.clear()
            list.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PrayerTimeVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_prayer_time,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_PRAYER_TIME -> updatePrayerTime(holder as PrayerTimeVH, position)
            TYPE_SUNRISE -> (holder as SunRiseTimeVH).updateView(list[position])
        }
    }

    private fun updatePrayerTime(holder: PrayerTimeVH, position: Int) {
        when (position) {
            0 -> {
                val timeLineView = TimelineView(holder.itemView.context, R.layout.view_time_line_top)
                holder.timeLineContainer.addView(timeLineView)
                holder.name.text = "Fajar"
                holder.name.text = "Fajar time"
            }
            list.size - 1 -> {
                val timeLineView = TimelineView(holder.itemView.context, R.layout.view_time_line_bottom)
                holder.timeLineContainer.addView(timeLineView)
                holder.name.text = "Isha"
                holder.name.text = "Isha time"
            }
            else -> {
                val timeLineView = TimelineView(holder.itemView.context, R.layout.view_time_line)
                holder.timeLineContainer.addView(timeLineView)
                holder.name.text = " -- "
                holder.name.text = " -- "
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class PrayerTimeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeLineContainer: LinearLayout = itemView.timelineContainer
        val name: TextView = itemView.name
        val time: TextView = itemView.time
    }

    class SunRiseTimeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun updateView(item: PrayerTimes) {}
    }

    companion object {
        const val TYPE_PRAYER_TIME: Int = 0
        const val TYPE_SUNRISE: Int = 1
    }
}
