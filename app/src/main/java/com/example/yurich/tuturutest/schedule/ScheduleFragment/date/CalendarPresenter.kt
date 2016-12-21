package com.example.yurich.tuturutest.schedule.ScheduleFragment.date

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.yurich.tuturutest.mvp.ScheduleView
import com.example.yurich.tuturutest.StationsApp
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.ResultQuery
import java.util.*
import javax.inject.Inject

/**
 * Created by yurich on 18.12.16.
 */
@InjectViewState
class CalendarPresenter : MvpPresenter<ScheduleView>() {

    @Inject
    lateinit var resultQuery: ResultQuery

    @Inject
    lateinit var repository: Repository

    init {
        StationsApp.appComponent.inject(this)
    }


    fun passDate(date: GregorianCalendar) {
        resultQuery.date = date
        Log.v("Repo", resultQuery.toString())
        if (resultQuery.isReady()) {
            viewState.displayResult(resultQuery)
        }
    }

    fun  isDateChosen() = resultQuery.date != null
}