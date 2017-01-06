package com.example.yurich.tuturutest.mvp

import com.arellomobile.mvp.MvpView
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity

/**
 * Created by yurich on 14.12.16.
 */
interface ScheduleView : MvpView {

    fun displayStations(entities: List<DisplayedEntity>)

    fun setupStations(entities: List<DisplayedEntity>)

    fun updateStations(entities: List<DisplayedEntity>)

    fun displayError(it: String)

    fun displayResult(query: ResultQuery)


    fun onStationsLoaded()

}