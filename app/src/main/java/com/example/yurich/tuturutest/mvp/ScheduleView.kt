package com.example.yurich.tuturutest.mvp

import com.arellomobile.mvp.MvpView
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity

/**
 * Created by yurich on 14.12.16.
 */
interface ScheduleView : MvpView {

    fun displayStations(entities: List<StoragedEntity>)
    fun updateStations(entities: List<StoragedEntity>)
    fun setupStations(entities: List<StoragedEntity>)

    fun displayError(it: String)

    fun displayResult(query: ResultQuery)

}