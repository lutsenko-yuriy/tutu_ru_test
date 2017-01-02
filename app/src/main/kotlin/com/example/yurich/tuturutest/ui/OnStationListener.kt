package com.example.yurich.tuturutest.ui

import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation

/**
 * Created by yurich on 18.12.16.
 */
interface OnStationListener {
    fun onStationClicked(station: DisplayedStation)
}