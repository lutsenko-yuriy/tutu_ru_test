package com.example.yurich.tuturutest.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity

/**
 * An abstract adapter delegate.
 * It is supposed to make creating complex RecyclerViews easier.
 * For further information read <a href="http://hannesdorfmann.com/android/adapter-delegates">this article</a>.
 * */
interface AbstractAdapterDelegate {

    fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DisplayedEntity)

    fun getSpanSize(): Int

}