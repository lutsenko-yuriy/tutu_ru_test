package com.example.yurich.tuturutest.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedCity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.utils.inflate
import kotlinx.android.synthetic.main.item_header.view.*

/**
 * An adapter delegate for headers
 */
class HeaderAdapterDelegate() : AbstractAdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HeaderHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DisplayedEntity) {
        (holder as HeaderHolder).bind(item as DisplayedCity)
    }

    inner class HeaderHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_header)
    ) {
        fun bind(item: DisplayedCity) {
            itemView.address_header.text = item.getAddress()
        }
    }

}