package com.example.yurich.tuturutest

import com.arellomobile.mvp.MvpPresenter
import rx.subscriptions.CompositeSubscription

/**
 * Created by yurich on 15.12.16.
 */
abstract class SchedulePresenter : MvpPresenter<ScheduleView>() {

    var subscriptions = CompositeSubscription()

    abstract fun retrieveAndShow()

    override fun detachView(view: ScheduleView?) {
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        super.detachView(view)
    }

}