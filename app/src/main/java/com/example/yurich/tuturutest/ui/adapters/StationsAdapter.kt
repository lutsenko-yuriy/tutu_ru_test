package com.example.yurich.tuturutest.ui.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity
import com.example.yurich.tuturutest.ui.OnStationListener

/**
 * Created by yurich on 15.12.16.
 */
class StationsAdapter(listener: OnStationListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapters = SparseArrayCompat<AbstractAdapterDelegate>()
    private var items: MutableList<StoragedEntity>

    init {
        adapters.put(StoragedEntity.CITY, HeaderAdapterDelegate())
        adapters.put(StoragedEntity.STATION, StationAdapterDelegate(listener))

        items = mutableListOf()
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

    fun setupStations(newEntities: List<StoragedEntity>) {
        items.addAll(newEntities)
        notifyItemRangeInserted(0, items.size)
    }

    fun updateStations(newEntities: List<StoragedEntity>) {
        items.clear()
        items.addAll(newEntities)
        notifyDataSetChanged()
    }

}