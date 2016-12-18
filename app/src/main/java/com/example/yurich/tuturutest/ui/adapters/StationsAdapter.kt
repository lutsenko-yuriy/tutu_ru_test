package com.example.yurich.tuturutest.ui.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import android.text.TextUtils
import android.view.ViewGroup
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.repository.local_storage.StoragedStation
import com.example.yurich.tuturutest.ui.OnStationListener
import java.util.*

/**
 * Created by yurich on 15.12.16.
 */
class StationsAdapter(listener: OnStationListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapters = SparseArrayCompat<AbstractAdapterDelegate>()
    private var items: ArrayList<StoragedEntity>

    init {
        adapters.put(StoragedEntity.CITY, HeaderAdapterDelegate())
        adapters.put(StoragedEntity.STATION, StationAdapterDelegate(listener))

        items = ArrayList()
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

    fun updateStations(newEntities: List<StoragedEntity>) {
        items.clear()
        notifyItemRangeRemoved(0, lastPosition())

        items.addAll(newEntities)
        notifyItemRangeInserted(0, items.size)
    }

    private fun lastPosition() = if (items.size == 0) 0 else items.size - 1

    fun find(needle: String, currPosition: Int): Int {
        if (lastPosition() != 0 && !TextUtils.isEmpty(needle)) {
            return (currPosition..lastPosition())
                    .firstOrNull {
                        items[it] is StoragedStation &&
                                (items[it] as StoragedStation).stationTitle.contains(needle, true)
                    }
                    ?: (0..currPosition)
                    .firstOrNull {
                        items[it] is StoragedStation &&
                                (items[it] as StoragedStation).stationTitle.contains(needle, true)
                    }
                    ?: 0
        } else {
            return 0
        }
    }

}