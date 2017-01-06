package com.example.yurich.tuturutest.schedule.ScheduleFragment.arrival

import android.text.TextUtils
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.yurich.tuturutest.mvp.SchedulePresenter
import com.example.yurich.tuturutest.repository.Repository
import com.example.yurich.tuturutest.repository.ResultQuery
import com.example.yurich.tuturutest.schedule.MainActivity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedCity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedEntity
import com.example.yurich.tuturutest.schedule.ScheduleFragment.DisplayedStation
import com.example.yurich.tuturutest.utils.DirectionConstants.ARRIVAL
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by yurich on 18.12.16.
 */
@InjectViewState
class ArrivalsPresenter : SchedulePresenter() {

    @Inject
    lateinit var resultQuery: ResultQuery

    @Inject
    lateinit var repository: Repository

    init {
        MainActivity.subcomponent.inject(this)
    }

    override fun retrieveAndShow(needle: String) {
        val listOfEntities = mutableListOf<DisplayedEntity>()
        var currentCity: DisplayedCity? = null

        subscriptions.add(
                repository.getStations()
                        .filter {
                            it.direction == ARRIVAL
                                    && (TextUtils.isEmpty(needle)
                                    || it.stationTitle.contains(needle, true))
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    if (currentCity == null || currentCity!!.cityId != it.cityId) {
                                        currentCity = DisplayedCity(it)
                                        listOfEntities.add(currentCity!!)
                                    }

                                    listOfEntities.add(it)
                                },
                                { viewState.displayError("Не удалось отобразить станции") },
                                {
                                    viewState.displayStations(listOfEntities)
                                    viewState.onStationsLoaded()
                                }
                        )
        )
    }

    override fun passStation(station: DisplayedStation) {
        resultQuery.arrivalStation = station
        Log.v("Repo", resultQuery.toString())
        viewState.displayResult(resultQuery)
    }

}