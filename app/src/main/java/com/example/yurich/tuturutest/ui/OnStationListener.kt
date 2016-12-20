package com.example.yurich.tuturutest.ui

import com.example.yurich.tuturutest.repository.local_storage.StoragedStation

/**
 * Created by yurich on 18.12.16.
 */
interface OnStationListener {
    fun onStationClicked(station: StoragedStation)
}