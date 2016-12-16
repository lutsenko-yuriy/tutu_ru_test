package com.example.yurich.tuturutest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by yurich on 16.12.16.
 */

// Extension function to inflate view
fun ViewGroup.inflate(resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId, this, attachToRoot)
}