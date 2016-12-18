package com.example.yurich.tuturutest.mvp

import com.arellomobile.mvp.MvpView
import com.example.yurich.tuturutest.repository.DataPackage
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity

/**
 * Created by yurich on 14.12.16.
 */
interface ScheduleView : MvpView {

    fun updateStations(entities: List<StoragedEntity>)

    fun displayError(it: Throwable)

    fun displayResult(query: ResultQuery)

}