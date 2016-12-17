package com.example.yurich.tuturutest

import com.arellomobile.mvp.MvpView
import com.example.yurich.tuturutest.repository.DataPackage
import com.example.yurich.tuturutest.repository.local_storage.StoragedEntity

/**
 * Created by yurich on 14.12.16.
 */
interface ScheduleView : MvpView {

    fun updateStations(entities: List<StoragedEntity>)

    fun <T> passSearchableData(data: DataPackage<T>)

    fun displayError(it: Throwable)

}