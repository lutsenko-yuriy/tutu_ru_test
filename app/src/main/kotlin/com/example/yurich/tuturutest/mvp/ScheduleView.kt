package com.example.yurich.tuturutest.mvp

import android.support.design.widget.Snackbar
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpView
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.result_alert_dialog_fragment.ResultAlertDialogFragment
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.ui.adapters.StationsAdapter
import kotlinx.android.synthetic.main.fragment_stations_list.*
import rx.subscriptions.CompositeSubscription

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