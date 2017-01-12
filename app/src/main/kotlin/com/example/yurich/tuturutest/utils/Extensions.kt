package com.example.yurich.tuturutest.utils

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yurich.tuturutest.R
import com.example.yurich.tuturutest.ui.OnStationListener
import com.example.yurich.tuturutest.ui.adapters.StationsAdapter
import java.util.*
import java.util.Calendar.*

/**
 * Created by yurich on 16.12.16.
 */

// Extension function to inflate view
fun ViewGroup.inflate(resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId, this, attachToRoot)
}

fun GregorianCalendar.display(): String {
    return "${this.get(DAY_OF_MONTH)}/${this.get(MONTH) + 1}/${this.get(YEAR)}"
}

// Resetting database
fun Context.fromLocalDb(): Boolean {
    val prefs = this.getSharedPreferences("fromLocalDb", Context.MODE_PRIVATE)
    val fromLocalDb = prefs.getBoolean("fromLocalDb", false)
    return fromLocalDb
}

fun Context.setDbUpdated(value: Boolean = false) {
    val editor = this.getSharedPreferences("fromLocalDb", Context.MODE_PRIVATE).edit()
    editor.putBoolean("fromLocalDb", value)
    editor.apply()
}

fun RecyclerView.initialize(context: Context, listener: OnStationListener) {
    val spanSize = resources.getInteger(R.integer.grid_span)
    this.layoutManager = GridLayoutManager(context, spanSize)
    this.adapter = StationsAdapter(spanSize, listener)

    (this.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return (adapter as StationsAdapter).getSpanSize(position)
        }
    }
}