package com.example.yurich.tuturutest.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

fun Context.isDbReseted(value: Boolean = false) {
    val editor = this.getSharedPreferences("fromLocalDb", Context.MODE_PRIVATE).edit()
    editor.putBoolean("fromLocalDb", value)
    editor.apply()
}