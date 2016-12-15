package com.example.yurich.tuturutest

import com.arellomobile.mvp.MvpPresenter
import com.example.yurich.tuturutest.network.City
import rx.Observable

/**
 * Created by yurich on 15.12.16.
 */
abstract class SchedulePresenter : MvpPresenter<ScheduleView>() {

    abstract fun retrieveAndShow()

}