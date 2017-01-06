package com.example.yurich.tuturutest.ui.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedCity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import com.example.yurich.tuturutest.ui.OnStationListener

/**
 * Created by yurich on 15.12.16.
 */
class StationsAdapter(listener: OnStationListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapters = SparseArrayCompat<AbstractAdapterDelegate>()
    private var items: MutableList<DisplayedEntity> = mutableListOf()

    init {
        adapters.put(DisplayedEntity.CITY, HeaderAdapterDelegate())
        adapters.put(DisplayedEntity.STATION, StationAdapterDelegate(listener))

    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapters[getItemViewType(position)]
                .onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapters[viewType].onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType()
    }

    fun setupStations(newEntities: List<DisplayedEntity>) {
        items.addAll(newEntities)
        notifyItemRangeInserted(0, items.size)
    }

    fun updateStations(newEntities: List<DisplayedEntity>) {
        items.clear()
        items.addAll(newEntities)
        notifyDataSetChanged()
    }

}