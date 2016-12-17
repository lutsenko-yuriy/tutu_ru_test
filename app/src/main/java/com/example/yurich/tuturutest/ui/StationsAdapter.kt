package com.example.yurich.tuturutest.ui

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import java.util.*

/**
 * Created by yurich on 15.12.16.
 */
class StationsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<StoragedEntity>
    private var adapters = SparseArrayCompat<AbstractAdapterDelegate>()

    init {
        adapters.put(StoragedEntity.CITY, HeaderAdapterDelegate())
        adapters.put(StoragedEntity.STATION, StationAdapterDelegate())

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

    private fun  lastPosition() = if (items.size == 0) 0 else items.size - 1

}