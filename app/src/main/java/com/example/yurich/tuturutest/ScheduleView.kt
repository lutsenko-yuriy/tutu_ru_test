package com.example.yurich.tuturutest

import com.arellomobile.mvp.MvpView
import com.example.yurich.tuturutest.network.City

/**
 * Created by yurich on 14.12.16.
 */
interface ScheduleView : MvpView {

    fun setData(cities: List<City>)

    fun <T> passSearchableData(data: DataPackage<T>)

    fun displayError(it: Throwable)

}