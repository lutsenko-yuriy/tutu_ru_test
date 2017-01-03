package com.example.yurich.tuturutest.mvp

import com.arellomobile.mvp.MvpPresenter
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import rx.subscriptions.CompositeSubscription

/**
 * Created by yurich on 18.12.16.
 */
abstract class SchedulePresenter : MvpPresenter<ScheduleView>() {

    var subscriptions = CompositeSubscription()

    override fun detachView(view: ScheduleView?) {
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        super.detachView(view)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        retrieveAndShow()
    }
    abstract fun retrieveAndShow(needle: String = "")

    abstract fun passStation(station: DisplayedStation)
}